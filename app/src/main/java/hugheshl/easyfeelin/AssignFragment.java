package hugheshl.easyfeelin;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static android.content.Context.ALARM_SERVICE;
import static android.widget.CompoundButton.*;

public class AssignFragment extends Fragment {

    private static final String ARG_ASSIGN_ID = "assign_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    private Assign mAssign;
    private EditText mTitleField;
    private Button mDateButton;
    private Button mLogButton;
    private ImageView face1ImageView;
    private ImageView face2ImageView;
    private ImageView face3ImageView;
    private ImageView face4ImageView;
    private ImageView face5ImageView;

    public static AssignFragment newInstance(UUID assignId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ASSIGN_ID, assignId);

        AssignFragment fragment = new AssignFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID assignId = (UUID) getArguments().getSerializable(ARG_ASSIGN_ID);
        mAssign = AssignLab.get(getActivity()).getAssign(assignId);
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_assign, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_assign:
                UUID assignID = mAssign.getId();
                AssignLab.get(getActivity()).deleteAssign(assignID);
                Toast.makeText(getActivity(), R.string.toast_delete_assign, Toast.LENGTH_SHORT).show();
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_assign, container, false);

        mTitleField = (EditText) v.findViewById(R.id.assign_title);
        mTitleField.setText(mAssign.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAssign.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mDateButton = (Button) v.findViewById(R.id.assign_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mAssign.getDate());
                dialog.setTargetFragment(AssignFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mLogButton = (Button) v.findViewById(R.id.log_button);
        mLogButton.setText("Recent Activities");
        mLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LogActivity.newIntent(getActivity(), mAssign.getId());
                startActivity(intent);
            }
        });

        face1ImageView = (ImageView) v.findViewById(R.id.imageViewFace1);
        face1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setMood();
                Toast toast = Toast.makeText(getActivity(), "[Placeholder] I'm Great!", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = LogActivity.newIntent(getActivity(), mAssign.getId());
                startActivity(intent);
            }
        });

        face2ImageView = (ImageView) v.findViewById(R.id.imageViewFace2);
        face2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setMood();
                Toast toast = Toast.makeText(getActivity(), "[Placeholder] I'm Good!", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = LogActivity.newIntent(getActivity(), mAssign.getId());
                startActivity(intent);
            }
        });

        face3ImageView = (ImageView) v.findViewById(R.id.imageViewFace3);
        face3ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setMood();
                Toast toast = Toast.makeText(getActivity(), "[Placeholder] I'm Ok", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = LogActivity.newIntent(getActivity(), mAssign.getId());
                startActivity(intent);
            }
        });

        face4ImageView = (ImageView) v.findViewById(R.id.imageViewFace4);
        face4ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setMood();
                Toast toast = Toast.makeText(getActivity(), "[Placeholder] I'm not good.", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = LogActivity.newIntent(getActivity(), mAssign.getId());
                startActivity(intent);
            }
        });

        face5ImageView = (ImageView) v.findViewById(R.id.imageViewFace5);
        face5ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setMood();
                Toast toast = Toast.makeText(getActivity(), "[Placeholder] Everything is shit!", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = LogActivity.newIntent(getActivity(), mAssign.getId());
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        AssignLab.get(getActivity()).updateAssign(mAssign);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mAssign.setDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(DateFormat.format("EEEE, MMM dd, yyyy", mAssign.getDate()).toString());
    }
}
