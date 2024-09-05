package statusbar.finder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;


public class LrcView extends FragmentActivity {
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lrcview);

        preferences = getSharedPreferences("offset", MODE_PRIVATE);

        TextView lrc =findViewById(R.id.lrc);
        TextView offset =findViewById(R.id.offset);
        if (MusicListenerService.instance.getLyric() == null)
            lrc.setText("none");
        else {
            String lrc1 = MusicListenerService.instance.getLyric().sentenceList.toString();
            String lrc2 = lrc1.substring(1, lrc1.length() - 1);
            String lrc3 = lrc2.replaceAll("\\d+: ", "");
            String lrc4 = lrc3.replaceAll(", ", "\n");
            lrc.setText(lrc4);
            offset.setText("offset: " + String.valueOf((MusicListenerService.instance.getLyric().offset > 0) ? -MusicListenerService.instance.getLyric().offset : Math.abs(MusicListenerService.instance.getLyric().offset)));
        }

        Button plus =findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MusicListenerService.instance.getLyric() != null) {
                    MusicListenerService.instance.setLyricOffset(MusicListenerService.instance.getLyric().offset - 100);
                    offset.setText("offset: " + String.valueOf((MusicListenerService.instance.getLyric().offset > 0) ? -MusicListenerService.instance.getLyric().offset : Math.abs(MusicListenerService.instance.getLyric().offset)));
                    if (MusicListenerService.instance.getLyric().offset == 0)
                        preferences.edit().remove("Title:" + MusicListenerService.instance.getLyric().title
                                + " ,Artist:" + MusicListenerService.instance.getLyric().artist
                                + " ,Album:" + MusicListenerService.instance.getLyric().album
                                + " ,By:" + MusicListenerService.instance.getLyric().by
                                + " ,Author:" + MusicListenerService.instance.getLyric().author
                                + " ,Length:" + MusicListenerService.instance.getLyric().length).apply();
                    else
                        preferences.edit().putInt("Title:" + MusicListenerService.instance.getLyric().title
                                        + " ,Artist:" + MusicListenerService.instance.getLyric().artist
                                        + " ,Album:" + MusicListenerService.instance.getLyric().album
                                        + " ,By:" + MusicListenerService.instance.getLyric().by
                                        + " ,Author:" + MusicListenerService.instance.getLyric().author
                                        + " ,Length:" + MusicListenerService.instance.getLyric().length,
                                MusicListenerService.instance.getLyric().offset).apply();
                }
            }
        });

        Button minus =findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MusicListenerService.instance.getLyric() != null) {
                    MusicListenerService.instance.setLyricOffset(MusicListenerService.instance.getLyric().offset + 100);
                    offset.setText("offset: " + String.valueOf((MusicListenerService.instance.getLyric().offset > 0) ? -MusicListenerService.instance.getLyric().offset : Math.abs(MusicListenerService.instance.getLyric().offset)));
                    if (MusicListenerService.instance.getLyric().offset == 0)
                        preferences.edit().remove("Title:" + MusicListenerService.instance.getLyric().title
                                + " ,Artist:" + MusicListenerService.instance.getLyric().artist
                                + " ,Album:" + MusicListenerService.instance.getLyric().album
                                + " ,By:" + MusicListenerService.instance.getLyric().by
                                + " ,Author:" + MusicListenerService.instance.getLyric().author
                                + " ,Length:" + MusicListenerService.instance.getLyric().length).apply();
                    else
                        preferences.edit().putInt("Title:" + MusicListenerService.instance.getLyric().title
                                        + " ,Artist:" + MusicListenerService.instance.getLyric().artist
                                        + " ,Album:" + MusicListenerService.instance.getLyric().album
                                        + " ,By:" + MusicListenerService.instance.getLyric().by
                                        + " ,Author:" + MusicListenerService.instance.getLyric().author
                                        + " ,Length:" + MusicListenerService.instance.getLyric().length,
                                MusicListenerService.instance.getLyric().offset).apply();
                }
            }
        });
    }
}
