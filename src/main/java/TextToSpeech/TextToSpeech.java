package TextToSpeech;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class TextToSpeech {
    public static void main(String[] args) {
        // Normal voice.
//        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        // Time voice.
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_time_awb.AlanVoiceDirectory");

        Voice voice = VoiceManager.getInstance().getVoice("alan");

        if (voice != null) {
            voice.allocate();
            voice.speak("The time is now, just half past nine");
            voice.deallocate();
        }
    }
}