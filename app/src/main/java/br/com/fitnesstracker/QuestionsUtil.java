package br.com.fitnesstracker;

import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.qanda.models.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuestionsUtil {

    private SharedPreferences mPreferenceManager;
    private Resources mResources;

    public QuestionsUtil(SharedPreferences mPreferenceManager, Resources mResources) {
        this.mPreferenceManager = mPreferenceManager;
        this.mResources = mResources;
    }

    private boolean isTrakingKey(String key) {
        return mPreferenceManager.getBoolean(key, true);
    }

    public ArrayList<Question> getQuestionsArray() {
        ArrayList<Question> questions = new ArrayList<>();

        HashMap<String, String> questionsItens = getPreferencesMap();

        for (Map.Entry<String, String> iten : questionsItens.entrySet()) {
            String question = iten.getKey();
            String preferenceKey = iten.getValue();

            if (isTrakingKey(preferenceKey)) {
                Question.AnswerType answerType = Question.AnswerType.DOUBLE;
                if (question.equals(mResources.getString(R.string.question_date))) {
                    answerType = Question.AnswerType.DATE;
                }
                Question questionObj = new Question(question, answerType);
                questions.add(questionObj);
            }
        }
        return questions;
    }

    private HashMap<String, String> getPreferencesMap() {
        HashMap<String, String> map = new HashMap<String, String>();

        String[] avaliationItens = mResources.getStringArray(R.array.fisical_avaliation_itens);
        String[] preferencesKeys = mResources.getStringArray(R.array.preference_keys_array);

        for (int i = 0; i<avaliationItens.length; i++) {
            map.put(avaliationItens[i], preferencesKeys[i]);
        }

        return map;
    }

}
