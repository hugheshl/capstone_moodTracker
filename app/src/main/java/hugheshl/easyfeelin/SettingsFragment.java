package hugheshl.easyfeelin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.UUID;

public class SettingsFragment extends DialogFragment {

    private EditText timerLength;
    private int timerLengthSetting;
    private Button changeRingtoneButton;

    public static SettingsFragment newInstance() {
        SettingsFragment settingsfragment = new SettingsFragment();
        return settingsfragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        if (savedInstanceState != null) {
        }

        timerLength = (EditText) view.findViewById(R.id.timerLength);
        timerLength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                timerLengthSetting = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        changeRingtoneButton = (Button) view.findViewById(R.id.ringtone_button);

        return view;
    }
}
