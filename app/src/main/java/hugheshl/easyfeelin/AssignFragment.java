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
import java.util.HashMap;
import java.util.Map;
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
    private ImageView face1ImageView;
    private ImageView face2ImageView;
    private ImageView face3ImageView;
    private ImageView face4ImageView;
    private ImageView face5ImageView;

    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;
    private CheckBox mCheckBox3;
    private CheckBox mCheckBox4;
    private CheckBox mCheckBox5;
    private CheckBox mCheckBox6;
    private CheckBox mCheckBox7;
    private CheckBox mCheckBox8;
    private CheckBox mCheckBox9;
    private CheckBox mCheckBox10;
    private CheckBox mCheckBox11;
    private CheckBox mCheckBox12;
    private CheckBox mCheckBox13;
    private CheckBox mCheckBox14;
    private CheckBox mCheckBox15;
    private StringBuilder activityString = new StringBuilder("");

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

        face1ImageView = (ImageView) v.findViewById(R.id.imageViewFace1);
        face2ImageView = (ImageView) v.findViewById(R.id.imageViewFace2);
        face3ImageView = (ImageView) v.findViewById(R.id.imageViewFace3);
        face4ImageView = (ImageView) v.findViewById(R.id.imageViewFace4);
        face5ImageView = (ImageView) v.findViewById(R.id.imageViewFace5);

        if(mAssign.getMood() == 1) {
            face1ImageView.setImageResource(R.drawable.face1_selected);
        }
        else if(mAssign.getMood() == 2) {
            face2ImageView.setImageResource(R.drawable.face2_selected);
        }
        else if(mAssign.getMood() == 3) {
            face3ImageView.setImageResource(R.drawable.face3_selected);
        }
        else if(mAssign.getMood() == 4) {
            face4ImageView.setImageResource(R.drawable.face4_selected);
        }
        else if(mAssign.getMood() == 5) {
            face5ImageView.setImageResource(R.drawable.face5_selected);
        }
        else {}

        face1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAssign.setMood(1);
                face1ImageView.setImageResource(R.drawable.face1_selected);
                face2ImageView.setImageResource(R.drawable.face2);
                face3ImageView.setImageResource(R.drawable.face3);
                face4ImageView.setImageResource(R.drawable.face4);
                face5ImageView.setImageResource(R.drawable.face5);
            }
        });

        face2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAssign.setMood(2);
                face1ImageView.setImageResource(R.drawable.face1);
                face2ImageView.setImageResource(R.drawable.face2_selected);
                face3ImageView.setImageResource(R.drawable.face3);
                face4ImageView.setImageResource(R.drawable.face4);
                face5ImageView.setImageResource(R.drawable.face5);
            }
        });

        face3ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAssign.setMood(3);
                face1ImageView.setImageResource(R.drawable.face1);
                face2ImageView.setImageResource(R.drawable.face2);
                face3ImageView.setImageResource(R.drawable.face3_selected);
                face4ImageView.setImageResource(R.drawable.face4);
                face5ImageView.setImageResource(R.drawable.face5);
            }
        });

        face4ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAssign.setMood(4);
                face1ImageView.setImageResource(R.drawable.face1);
                face2ImageView.setImageResource(R.drawable.face2);
                face3ImageView.setImageResource(R.drawable.face3);
                face4ImageView.setImageResource(R.drawable.face4_selected);
                face5ImageView.setImageResource(R.drawable.face5);
            }
        });

        face5ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAssign.setMood(5);
                face1ImageView.setImageResource(R.drawable.face1);
                face2ImageView.setImageResource(R.drawable.face2);
                face3ImageView.setImageResource(R.drawable.face3);
                face4ImageView.setImageResource(R.drawable.face4);
                face5ImageView.setImageResource(R.drawable.face5_selected);
            }
        });

        if (mAssign.getLogs() == null) {
            activityString.append("000000000000000");
        }
        else {
            activityString.delete(0, 15);
            activityString.append(mAssign.getLogs());
        }

        mCheckBox1 = (CheckBox) v.findViewById(R.id.checkBox1);
        mCheckBox1.setChecked(activityString.charAt(0) == '1');
        mCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(0, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(0, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox2 = (CheckBox) v.findViewById(R.id.checkBox2);
        mCheckBox2.setChecked(activityString.charAt(1) == '1');
        mCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(1, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(1, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox3 = (CheckBox) v.findViewById(R.id.checkBox3);
        mCheckBox3.setChecked(activityString.charAt(2) == '1');
        mCheckBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(2, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(2, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox4 = (CheckBox) v.findViewById(R.id.checkBox4);
        mCheckBox4.setChecked(activityString.charAt(3) == '1');
        mCheckBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(3, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(3, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox5 = (CheckBox) v.findViewById(R.id.checkBox5);
        mCheckBox5.setChecked(activityString.charAt(4) == '1'); // STRING INDEX OUT OF BOUNDS ******* when make new entry only
        mCheckBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(4, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(4, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox6 = (CheckBox) v.findViewById(R.id.checkBox6);
        mCheckBox6.setChecked(activityString.charAt(5) == '1');
        mCheckBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(5, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(5, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox7 = (CheckBox) v.findViewById(R.id.checkBox7);
        mCheckBox7.setChecked(activityString.charAt(6) == '1');
        mCheckBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(6, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(6, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox8 = (CheckBox) v.findViewById(R.id.checkBox8);
        mCheckBox8.setChecked(activityString.charAt(7) == '1');
        mCheckBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(7, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(7, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox9 = (CheckBox) v.findViewById(R.id.checkBox9);
        mCheckBox9.setChecked(activityString.charAt(8) == '1');
        mCheckBox9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(8, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(8, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox10 = (CheckBox) v.findViewById(R.id.checkBox10);
        mCheckBox10.setChecked(activityString.charAt(9) == '1');
        mCheckBox10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(9, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(9, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox11 = (CheckBox) v.findViewById(R.id.checkBox11);
        mCheckBox11.setChecked(activityString.charAt(10) == '1');
        mCheckBox11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(10, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(10, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox12 = (CheckBox) v.findViewById(R.id.checkBox12);
        mCheckBox12.setChecked(activityString.charAt(11) == '1');
        mCheckBox12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(11, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(11, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox13 = (CheckBox) v.findViewById(R.id.checkBox13);
        mCheckBox13.setChecked(activityString.charAt(12) == '1');
        mCheckBox13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(12, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(12, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox14 = (CheckBox) v.findViewById(R.id.checkBox14);
        mCheckBox14.setChecked(activityString.charAt(13) == '1');
        mCheckBox14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(13, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(13, '0');
                    mAssign.setLogs(activityString.toString());
                }
            }
        });

        mCheckBox15 = (CheckBox) v.findViewById(R.id.checkBox15);
        mCheckBox15.setChecked(activityString.charAt(14) == '1');
        mCheckBox15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activityString.setCharAt(14, '1');
                    mAssign.setLogs(activityString.toString());
                } else {
                    activityString.setCharAt(14, '0');
                    mAssign.setLogs(activityString.toString());
                }
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
