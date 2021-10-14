package com.example.dicv2;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class TextToSpeech {
    public static void main(String[] args) {
        // Normal voice.
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");


        Voice voice = VoiceManager.getInstance().getVoice("kevin");

        if (voice != null) {
            voice.allocate();
            voice.speak("good morning");
            voice.deallocate();
        }
    }
}