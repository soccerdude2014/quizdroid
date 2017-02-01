package edu.washington.jesusm14.quizdroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnswerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "givenanswer";
    private static final String ARG_PARAM2 = "actualanswer";
    private static final String ARG_PARAM3 = "score";
    private static final String ARG_PARAM4 = "total";

    // TODO: Rename and change types of parameters
    private String givenAnswer;
    private String actualAnswer;
    private int score;
    private int total;
    private View view;

    private OnFragmentInteractionListener mListener;

    public AnswerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param givenAnswer Parameter 1.
     * @param actualAnswer Parameter 2.
     * @return A new instance of fragment AnswerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnswerFragment newInstance(String givenAnswer, String actualAnswer, int score, int total) {
        AnswerFragment fragment = new AnswerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, givenAnswer);
        args.putString(ARG_PARAM2, actualAnswer);
        args.putInt(ARG_PARAM3, score);
        args.putInt(ARG_PARAM4, total);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            givenAnswer = getArguments().getString(ARG_PARAM1);
            actualAnswer = getArguments().getString(ARG_PARAM2);
            score = getArguments().getInt(ARG_PARAM3);
            total = getArguments().getInt(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_answer, container, false);
        TextView userAnswer = (TextView) view.findViewById(R.id.useranswer);
        userAnswer.setText("Your Answer: " + givenAnswer);
        TextView actualAnswer = (TextView) view.findViewById(R.id.actualanswer);
        actualAnswer.setText("Actual Answer: " + actualAnswer);
        TextView scoreView = (TextView) view.findViewById(R.id.score);
        scoreView.setText("You have " + score + " correct out of " + total);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

/*
private Button submit;
    private Intent prev;
    private String[] correct; //
    private int num; //
    private String[] questions; //
    private Bundle bundle;
    private String[][] answers; //
    private TextView givenAnswer;
    private TextView correctAnswer;
    private TextView numberCorrect;
    private int[] rightWrong; //
    private String given;
    private Intent next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        prev = getIntent();
        bundle = prev.getExtras();
        num = prev.getIntExtra("questionnumber", 0);
        correct = prev.getStringArrayExtra("correct");
        questions = prev.getStringArrayExtra("questions");
        answers = (String[][]) bundle.getSerializable("answers");
        rightWrong = prev.getIntArrayExtra("rightwrong");
        given = prev.getStringExtra("given");
        submit = (Button) findViewById(R.id.finishornext);
        givenAnswer = (TextView) findViewById(R.id.answergiven);
        correctAnswer = (TextView) findViewById(R.id.correctanswer);
        numberCorrect = (TextView) findViewById(R.id.numbercorrect);
        givenAnswer.setText("Your answer" + given);
        correctAnswer.setText("The Correct Answer" + correct[num]);
        numberCorrect.setText("You have " + rightWrong[0] + " out of " + rightWrong[1] + " correct!");
        submit = (Button) findViewById(R.id.finishornext);
        num++;

        if(num == questions.length) {
            submit.setText("Finish");
            next = new Intent(AnswerActivity.this, MainActivity.class);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                startActivity(next);
                }
            });
        } else {
            submit.setText("Next");
            next = new Intent(AnswerActivity.this, Quiz.class);
            next.putExtra("questions", questions);
            next.putExtra("questionnumber", num);
            next.putExtra("correct", correct);
            next.putExtra("rightwrong", rightWrong);
            next.putExtras(bundle);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(next);
                }
            });
        }

*/

