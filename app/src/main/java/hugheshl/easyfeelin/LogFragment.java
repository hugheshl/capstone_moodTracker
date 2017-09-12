package hugheshl.easyfeelin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.UUID;

public class LogFragment extends DialogFragment {

    private Assign mAssign;
    private ImageButton mActionButton;

    public static LogFragment newInstance(UUID assignId) {
        LogFragment fragment = new LogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID assignID = (UUID) getActivity().getIntent().getSerializableExtra(LogActivity.EXTRA_ASSIGN_ID);
        mAssign = AssignLab.get(getActivity()).getAssign(assignID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        if (savedInstanceState != null) {
        }

        return view;
    }
}
