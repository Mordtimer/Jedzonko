package com.example.jedzonko.model

import com.google.gson.annotations.SerializedName

data class Nutriments (
        @SerializedName("energy_100g")
        val energy100g:String,
        @SerializedName("energy-kj_100g")
        val energykj100g:String,
        @SerializedName("energy-kcal_100g")
        val energykcal100g:String,
        @SerializedName("proteins_100g")
        val proteins100g:String,
        @SerializedName("casein_100g")
        val casein100g:String,
        @SerializedName("serum-proteins_100g")
        val serumproteins100g:String,
        @SerializedName("nucleotides_100g")
        val nucleotides100g:String,
        @SerializedName("carbohydrates_100g")
        val carbohydrates100g:String,
        @SerializedName("sugars_100g")
        val sugars100g:String,
        @SerializedName("sucrose_100g")
        val sucrose100g:String,
        @SerializedName("glucose_100g")
        val glucose100g:String,
        @SerializedName("fructose_100g")
        val fructose100g:String,
        @SerializedName("lactose_100g")
        val lactose100g:String,
        @SerializedName("maltose_100g")
        val maltose100g:String,
        @SerializedName("maltodextrins_100g")
        val maltodextrins100g:String,
        @SerializedName("starch_100g")
        val starch100g:String,
        @SerializedName("polyols_100g")
        val polyols100g:String,
        @SerializedName("fat_100g")
        val fat100g:String,
        @SerializedName("saturated-fat_100g")
        val saturatedfat100g:String,
        @SerializedName("butyric-acid_100g")
        val butyricacid100g:String,
        @SerializedName("caproic-acid_100g")
        val caproicacid100g:String,
        @SerializedName("caprylic-acid_100g")
        val caprylicacid100g:String,
        @SerializedName("capric-acid_100g")
        val capricacid100g:String,
        @SerializedName("lauric-acid_100g")
        val lauricacid100g:String,
        @SerializedName("myristic-acid_100g")
        val myristicacid100g:String,
        @SerializedName("palmitic-acid_100g")
        val palmiticacid100g:String,
        @SerializedName("stearic-acid_100g")
        val stearicacid100g:String,
        @SerializedName("arachidic-acid_100g")
        val arachidicacid100g:String,
        @SerializedName("behenic-acid_100g")
        val behenicacid100g:String,
        @SerializedName("lignoceric-acid_100g")
        val lignocericacid100g:String,
        @SerializedName("cerotic-acid_100g")
        val ceroticacid100g:String,
        @SerializedName("montanic-acid_100g")
        val montanicacid100g:String,
        @SerializedName("melissic-acid_100g")
        val melissicacid100g:String,
        @SerializedName("monounsaturated-fat_100g")
        val monounsaturatedfat100g:String,
        @SerializedName("polyunsaturated-fat_100g")
        val polyunsaturatedfat100g:String,
        @SerializedName("omega-3-fat_100g")
        val omega3fat100g:String,
        @SerializedName("alpha-linolenic-acid_100g")
        val alphalinolenicacid100g:String,
        @SerializedName("eicosapentaenoic-acid_100g")
        val eicosapentaenoicacid100g:String,
        @SerializedName("docosahexaenoic-acid_100g")
        val docosahexaenoicacid100g:String,
        @SerializedName("omega-6-fat_100g")
        val omega6fat100g:String,
        @SerializedName("linoleic-acid_100g")
        val linoleicacid100g:String,
        @SerializedName("arachidonic-acid_100g")
        val arachidonicacid100g:String,
        @SerializedName("gamma-linolenic-acid_100g")
        val gammalinolenicacid100g:String,
        @SerializedName("dihomo-gamma-linolenic-acid_100g")
        val dihomogammalinolenicacid100g:String,
        @SerializedName("omega-9-fat_100g")
        val omega9fat100g:String,
        @SerializedName("oleic-acid_100g")
        val oleicacid100g:String,
        @SerializedName("elaidic-acid_100g")
        val elaidicacid100g:String,
        @SerializedName("gondoic-acid_100g")
        val gondoicacid100g:String,
        @SerializedName("mead-acid_100g")
        val meadacid100g:String,
        @SerializedName("erucic-acid_100g")
        val erucicacid100g:String,
        @SerializedName("nervonic-acid_100g")
        val nervonicacid100g:String,
        @SerializedName("trans-fat_100g")
        val transfat100g:String,
        @SerializedName("cholesterol_100g")
        val cholesterol100g:String,
        @SerializedName("fiber_100g")
        val fiber100g:String,
        @SerializedName("sodium_100g")
        val sodium100g:String,
        @SerializedName("alcohol_100g")
        val alcohol100g:String,
        @SerializedName("vitamin-a_100g")
        val vitamina100g:String,
        @SerializedName("vitamin-d_100g")
        val vitamind100g:String,
        @SerializedName("vitamin-e_100g")
        val vitamine100g:String,
        @SerializedName("vitamin-k_100g")
        val vitamink100g:String,
        @SerializedName("vitamin-c_100g")
        val vitaminc100g:String,
        @SerializedName("vitamin-b1_100g")
        val vitaminb1100g:String,
        @SerializedName("vitamin-b2_100g")
        val vitaminb2100g:String,
        @SerializedName("vitamin-pp_100g")
        val vitaminpp100g:String,
        @SerializedName("vitamin-b6_100g")
        val vitaminb6100g:String,
        @SerializedName("vitamin-b9_100g")
        val vitaminb9100g:String,
        @SerializedName("vitamin-b12_100g")
        val vitaminb12100g:String,
        @SerializedName("biotin_100g")
        val biotin100g:String,
        @SerializedName("pantothenic-acid_100g")
        val pantothenicacid100g:String,
        @SerializedName("silica_100g")
        val silica100g:String,
        @SerializedName("bicarbonate_100g")
        val bicarbonate100g:String,
        @SerializedName("potassium_100g")
        val potassium100g:String,
        @SerializedName("chloride_100g")
        val chloride100g:String,
        @SerializedName("calcium_100g")
        val calcium100g:String,
        @SerializedName("phosphorus_100g")
        val phosphorus100g:String,
        @SerializedName("iron_100g")
        val iron100g:String,
        @SerializedName("magnesium_100g")
        val magnesium100g:String,
        @SerializedName("zinc_100g")
        val zinc100g:String,
        @SerializedName("copper_100g")
        val copper100g:String,
        @SerializedName("manganese_100g")
        val manganese100g:String,
        @SerializedName("fluoride_100g")
        val fluoride100g:String,
        @SerializedName("selenium_100g")
        val selenium100g:String,
        @SerializedName("chromium_100g")
        val chromium100g:String,
        @SerializedName("molybdenum_100g")
        val molybdenum100g:String,
        @SerializedName("iodine_100g")
        val iodine100g:String,
        @SerializedName("caffeine_100g")
        val caffeine100g:String,
        @SerializedName("taurine_100g")
        val taurine100g:String,
        @SerializedName("ph_100g")
        val ph100g:String,
        @SerializedName("fruits-vegetables-nuts_100g")
        val fruitsvegetablesnuts100g:String,
)