package com.example.student.geoquiz;
//Tristan Wood
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String MYTAG = "quizactivity";
    private static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;
 //   private Toast mToast;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    private int mCurrentIndex=0;

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

    private void toggleButton(){
        if (mTrueButton.isEnabled() == true)
        {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
            }else
            if (mTrueButton.isEnabled() == false)
            {
                mTrueButton.setEnabled(true);
                mFalseButton.setEnabled(true);
                }
    };

    @Override
    public void onStart() {
        super.onStart();
        Log.d(MYTAG, "called onStart");
    }

    public void onResume() {
        super.onResume();
        Log.d(MYTAG, "called onResume");
    }

    public void onPause() {
        super.onPause();
        Log.d(MYTAG, "called onPause");
    }

    public void onStop() {
        super.onStop();
        Log.d(MYTAG, "called onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(MYTAG, "called onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null) mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        Log.d(MYTAG,"called onCreate");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer(true);
                        toggleButton();
                    }

                }
        );
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                toggleButton();
            }
        });
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//                        if(mCurrentIndex==mQuestionBank.length) { mCurrentIndex=0; }
                        updateQuestion();
                    }

                }
        );
        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
//                        if(mCurrentIndex==mQuestionBank.length) { mCurrentIndex=0; }
                        updateQuestion();
                    }

                }
        );
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        if(userPressedTrue==mQuestionBank[mCurrentIndex].isAnswerTrue())
           Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT).show();
       else
           Toast.makeText(QuizActivity.this,R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
    }
}