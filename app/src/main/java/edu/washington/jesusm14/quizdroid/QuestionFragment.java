package edu.washington.jesusm14.quizdroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "question";
    private static final String ARG_PARAM2 = "answers";
    private static final String ARG_PARAM3 = "correctanswer";

    // TODO: Rename and change types of parameters
    private String questions;
    private String[] answers;
    private String correct;
    private String selectedText;
    private RadioGroup choices;
    private View view;
    private RadioButton selection;

    private OnFragmentInteractionListener mListener;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question Parameter 1.
     * @param answers Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(String question, String[] answers, String correct) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, question);
        args.putStringArray(ARG_PARAM2, answers);
        args.putString(ARG_PARAM3, correct);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questions = getArguments().getString(ARG_PARAM1);
            answers = getArguments().getStringArray(ARG_PARAM2);
            correct = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_question, container, false);
        TextView questionDisplay = (TextView) view.findViewById(R.id.questiondisplay);
        questionDisplay.setText("Question:" + questions);
        RadioButton option1 = (RadioButton) view.findViewById(R.id.radio1);
        option1.setText(answers[0]);
        RadioButton option2 = (RadioButton) view.findViewById(R.id.radio2);
        option2.setText(answers[1]);
        RadioButton option3 = (RadioButton) view.findViewById(R.id.radio3);
        option3.setText(answers[2]);
        RadioButton option4 = (RadioButton) view.findViewById(R.id.radio4);
        option4.setText(answers[3]);
        choices = (RadioGroup) view.findViewById(R.id.radiogroup);

        choices.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int id) {
                int selectedInt = choices.getCheckedRadioButtonId();
                passAnswerSelected(true);
                selection = (RadioButton) view.findViewById(selectedInt);
                selectedText = (String) selection.getText();
                passGivenAnswer(selectedText);
                if(selectedText.equals(correct)) {
                    passAnswerCorrect(true);
                } else {
                    passAnswerCorrect(false);
                }
            }
        });
        return view;

    }

    public void passAnswerSelected(boolean data) {
        mListener.isAnswerChecked(data);
    }

    public void passAnswerCorrect(boolean data) {
        mListener.isAnswerCorrect(data);
    }

    public void passGivenAnswer(String data) {
        mListener.givenAnswer(data);
    }

    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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
        void isAnswerChecked(boolean result);
        void isAnswerCorrect(boolean result);
        void givenAnswer(String given);
    }
}

/*

  private RadioGroup choices;
    private RadioButton selection;
    private Button submit;
    private Intent prev;
    private String[] correct;
    private int num;
    private String[] questions;
    private Bundle bundle;
    private String[][] answers;
    private TextView question;
    private int[] rightWrong;
    private String selectedText;

private GoogleApiClient mClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_form);
        prev = getIntent();
        bundle = prev.getExtras();
        num = prev.getIntExtra("questionnumber", 0);
        correct = prev.getStringArrayExtra("correct");
        questions = prev.getStringArrayExtra("questions");
        answers = (String[][]) bundle.getSerializable("answers");
        RadioButton option1 = (RadioButton) findViewById(R.id.firstoption);
        RadioButton option2 = (RadioButton) findViewById(R.id.secondoption);
        RadioButton option3 = (RadioButton) findViewById(R.id.thirdoption);
        RadioButton option4 = (RadioButton) findViewById(R.id.fourthoption);
        option1.setText(answers[num][0]);
        option2.setText(answers[num][1]);
        option3.setText(answers[num][2]);
        option4.setText(answers[num][3]);
        question = (TextView) findViewById(R.id.question);
        question.setText(questions[num]);
        rightWrong = new int[]{0, questions.length};


        choices = (RadioGroup) findViewById(R.id.choices);
        submit = (Button) findViewById(R.id.submit_button);
        choices.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int id) {
                int selectedInt = choices.getCheckedRadioButtonId();
                selection = (RadioButton) findViewById(selectedInt);
                selectedText = (String) selection.getText();
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selectedText.equals(correct[num])) {
                            rightWrong[0]++;
                        }
                        Intent next = new Intent(Quiz.this, AnswerActivity.class);
                        next.putExtra("questions", questions);
                        next.putExtra("questionnumber", num);
                        next.putExtra("correct", correct);
                        next.putExtra("rightwrong", rightWrong);
                        next.putExtras(bundle);
                        next.putExtra("given", selection.getText());
                        startActivity(next);
                    }
                });
            }
        });
*/
