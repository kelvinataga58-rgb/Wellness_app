package com.kelvin.simba_wellnesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

//    A variable to store our interstital ad
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)
//        It starts the google Admob SDK
//        It prepares your app to load the ad
//        Must be called befoe showing ads

        val adView = findViewById<AdView>(R.id.adView)
//        Get the Adview from Layout
//        Connects your kotlin code to the ad view in XML

        val adRequest = AdRequest.Builder().build()
//        Creates a request asking Admob for an ad


        adView.loadAd(adRequest)
//        Loads the ad
//        Sends the request to Admob


        loadInterstitialAd()
//        call the function to load the ad from the server



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Finding ids using find view by id from the layout
//        create a variable to store the button

        val recipe=findViewById<Button>(R.id.recipes)
//        set onClick listener

        recipe.setOnClickListener {

//            enable our intent

            val recipeIntent= Intent(applicationContext, HealthyRecipes::class.java)
            startActivity(recipeIntent)
            showInterstitialAd()
//            show you ad here
        }


//        nutrition intent

        val nutrition= findViewById<Button>(R.id.nutrition)

        nutrition.setOnClickListener {

            val nutritionIntent=Intent(applicationContext, NutritionAdvice::class.java)
            startActivity(nutritionIntent)
        }


//        Meditation intent

        val meditation= findViewById<Button>(R.id.meditation)

        meditation.setOnClickListener {
            val meditationIntent= Intent(applicationContext, Meditation::class.java)
            startActivity(meditationIntent)
            showInterstitialAd()
        }

        val hydration= findViewById<Button>(R.id.hydration)

        hydration.setOnClickListener {

            val hydrationIntent= Intent(applicationContext, HydrationAlert::class.java)
            startActivity(hydrationIntent)
        }

        val exercise= findViewById<Button>(R.id.exercise)
        exercise.setOnClickListener {

            val exericeIntent= Intent(applicationContext, StartExercise::class.java)
            startActivity(exericeIntent)
        }

        val motivation= findViewById<Button>(R.id.motivation)
        motivation.setOnClickListener {

            val motivationIntent= Intent(applicationContext, DailyMotivation::class.java)
            startActivity(motivationIntent)
        }

        val weekly= findViewById<Button>(R.id.goals)
        weekly.setOnClickListener{
            val weeklyIntent= Intent(applicationContext, WeeklyGoals::class.java)
            startActivity(weeklyIntent)
        }

        val progress= findViewById<Button>(R.id.progress)
        progress.setOnClickListener {
            val progressIntent= Intent(applicationContext, CheckProgress::class.java)
            startActivity(progressIntent)
        }






    }
//Is a function to load our ad from the server
    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712", // Test ID
            adRequest,
            object : InterstitialAdLoadCallback() {

                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }
        )
    }
    //Show Interstitial ad
    fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }


}
