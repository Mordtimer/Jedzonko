package com.example.jedzonko.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.jedzonko.MainActivity
import com.example.jedzonko.R
import com.example.jedzonko.databinding.ScanFragmentBinding
import com.example.jedzonko.viewModel.CameraXVM
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class ScanFragment() : Fragment(R.layout.scan_fragment), ActivityCompat.OnRequestPermissionsResultCallback {

    private var _binding: ScanFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var cameraXVM: CameraXVM
    private lateinit var productCode: String

    //private lateinit var productViewModel: ProductViewModel
    private var lensFacing = CameraSelector.LENS_FACING_BACK
    private var cameraProvider: ProcessCameraProvider? = null
    private var cameraSelector: CameraSelector? = null
    private var previewUseCase: Preview? = null
    private var analysisUseCase: ImageAnalysis? = null
    private var previewView: PreviewView? = null

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
        cameraXVM = ViewModelProvider(requireActivity()).get(CameraXVM::class.java)
        _binding = ScanFragmentBinding.inflate(
            layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toggleButtonFlash.isChecked = false
        setupCamera(view)
    }

    private fun toggleFlash(control: CameraControl){
            val flashButton = binding.toggleButtonFlash
            flashButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    control.enableTorch(true)
                } else {
                    control.enableTorch(false)
                }
        }
    }

    private fun setupCamera(view: View) {
        previewView = binding.previewView
        cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
        ViewModelProvider(
                this, ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(CameraXVM::class.java)
                .processCameraProvider
                .observe(viewLifecycleOwner, { provider: ProcessCameraProvider? ->
                    cameraProvider = provider
                    if (isCameraPermissionGranted()) {
                        bindPreviewUseCase()
                        bindAnalyseUseCase()
                    } else {
                        requestPermissions(
                            arrayOf(Manifest.permission.CAMERA),
                            PERMISSION_CAMERA_REQUEST)
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
            val control = cameraProvider!!.bindToLifecycle(/* lifecycleOwner= */this,
                    cameraSelector!!,
                    previewUseCase
            )
            toggleFlash(control.cameraControl)
        }catch (illegalStateException: IllegalStateException){
            illegalStateException.message?.let { Log.e("idk", it)
            }
        } catch (illegalArgumentException: IllegalArgumentException){
            illegalArgumentException.message?.let { Log.e("idk", it) }
        }
    }

    private fun bindAnalyseUseCase() {
        val barcodeScanner: BarcodeScanner = BarcodeScanning.getClient()

        if (cameraProvider == null) {
            return
        }
        if (analysisUseCase != null) {
            cameraProvider!!.unbind(analysisUseCase)
        }

        analysisUseCase = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()


        // Initialize our background executor
        val cameraExecutor = Executors.newSingleThreadExecutor()

        analysisUseCase?.setAnalyzer(cameraExecutor, { imageProxy ->
            processImageProxy(barcodeScanner, imageProxy)
        })

        try {
            cameraProvider!!.bindToLifecycle(/* lifecycleOwner= */this,
                    cameraSelector!!,
                    analysisUseCase
            )
        } catch (illegalStateException: IllegalStateException) {
            illegalStateException.message?.let { Log.e("idk", it) }
        } catch (illegalArgumentException: IllegalArgumentException) {
            illegalArgumentException.message?.let { Log.e("idk", it) }
        }
    }

    @SuppressLint("UnsafeExperimentalUsageError")
    private fun processImageProxy(
            barcodeScanner: BarcodeScanner,
            imageProxy: ImageProxy,
    ) {
        val inputImage =
                InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)

        barcodeScanner.process(inputImage)
                .addOnSuccessListener { barcodes ->

                    barcodes.forEach {
                        it.rawValue?.let { it1 -> Log.d("Barcode", it1)}
                        if(it.rawValue != null){
                            //productViewModel.productCode = it.rawValue
                            productCode = it.rawValue!!
                            imageProxy.close()
                            if(view != null) {
                                val action = ScanFragmentDirections.actionScanFragmentToProductFragment(productCode)
                                if (view?.findNavController()?.currentDestination?.id == R.id.scanFragment) {
                                    findNavController().navigate(action)
                                }
                            }
                        }
                    }
                }
                .addOnFailureListener {
                    it.message?.let { it1 -> Log.e("idk", it1) }
                }.addOnCompleteListener {
                    imageProxy.close()
                }
    }

    private fun isCameraPermissionGranted(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
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
            if (isCameraPermissionGranted(requireContext())) {
                setupCamera(requireView())
            } else {
                Log.i(TAG, "Permission to camera denied")
                //todo jeśli nie przydzielone wyświetlić przycisk z ponownym uruchomieniem zapytania o uprawnienia
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
        const val PERMISSION_CAMERA_REQUEST = 1

        private const val RATIO_4_3_VALUE = 4.0 / 3.0
        private const val RATIO_16_9_VALUE = 16.0 / 9.0
    }
}