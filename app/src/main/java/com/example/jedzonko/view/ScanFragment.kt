package com.example.jedzonko.view

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jedzonko.R
import com.example.jedzonko.viewModel.CameraXViewModel
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class ScanFragment() : Fragment() {
    private lateinit var cameraXViewModel: CameraXViewModel
    private var previewView: PreviewView? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private var cameraSelector: CameraSelector? = null
    private var lensFacing = CameraSelector.LENS_FACING_BACK
    private var previewUseCase: Preview? = null

    private val screenAspectRatio: Int
        get() {
            // Get screen metrics used to setup camera for full screen resolution
            val metrics = DisplayMetrics().also { previewView?.display?.getRealMetrics(it) }
            return aspectRatio(metrics.widthPixels, metrics.heightPixels)
        }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        cameraXViewModel = ViewModelProvider(requireActivity()).get(CameraXViewModel::class.java)

        return inflater.inflate(R.layout.scan_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCamera(requireView())
    }

    private fun setupCamera(view:View) {
        previewView = view.findViewById(R.id.preview_view)
        cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
        ViewModelProvider(
                this, ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(CameraXViewModel::class.java)
                .processCameraProvider
                .observe(viewLifecycleOwner, Observer { provider: ProcessCameraProvider? ->
                    cameraProvider = provider
                    if (isCameraPermissionGranted()) {
                        bindPreviewUseCase()
                    } else {
                        ActivityCompat.requestPermissions(
                                requireActivity(),
                                arrayOf(Manifest.permission.CAMERA),
                                PERMISSION_CAMERA_REQUEST
                        )
                    }
                }
                )
    }

    private fun bindPreviewUseCase() {
        if (cameraProvider == null) {
            return
        }
        if (previewUseCase != null) {
            cameraProvider!!.unbind(previewUseCase)
        }

        previewUseCase = Preview.Builder()
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(previewView!!.display.rotation)
                .build()
        previewUseCase!!.setSurfaceProvider(previewView!!.createSurfaceProvider())

        try {
            cameraProvider!!.bindToLifecycle(/* lifecycleOwner= */this,
                    cameraSelector!!,
                    previewUseCase
            )
        }catch (illegalStateException: IllegalStateException){
            illegalStateException.message?.let { Log.e(TAG, it) }
        } catch (illegalArgumentException: IllegalArgumentException){
            illegalArgumentException.message?.let { Log.e(TAG, it) }
        }
    }
    private fun aspectRatio(width: Int, height: Int): Int {
        val previewRatio = max(width, height).toDouble() / min(width, height)
        if (abs(previewRatio - RATIO_4_3_VALUE) <= abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3
        }
        return AspectRatio.RATIO_16_9
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_CAMERA_REQUEST) {
            if (isCameraPermissionGranted()) {
                bindPreviewUseCase()
            } else {
                Log.e(TAG, "no camera permission")
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun isCameraPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val PERMISSION_CAMERA_REQUEST = 1

        private const val RATIO_4_3_VALUE = 4.0 / 3.0
        private const val RATIO_16_9_VALUE = 16.0 / 9.0
    }

}