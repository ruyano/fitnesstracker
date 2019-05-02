package br.com.fitnesstracker.util;

import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.qanda.models.Question;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.fitnesstracker.R;
import br.com.fitnesstracker.models.FisicalAvaliation;

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

    public ArrayList<String> getQuestionsStringArrayForSpinner() {
        ArrayList<String> questions = new ArrayList<>();
        LinkedHashMap<String, String> questionsItens = getPreferencesMap();
        for (Map.Entry<String, String> iten : questionsItens.entrySet()) {
            String question = iten.getKey();
            String preferenceKey = iten.getValue();
            if (isTrakingKey(preferenceKey) && !question.equals(mResources.getString(R.string.question_date))) {
                questions.add(question);
            }
        }
        return questions;
    }

    public ArrayList<Question> getQuestionsArray() {
        ArrayList<Question> questions = new ArrayList<>();
        LinkedHashMap<String, String> questionsItens = getPreferencesMap();
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

    private LinkedHashMap<String, String> getPreferencesMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        String[] avaliationItens = mResources.getStringArray(R.array.fisical_avaliation_itens);
        String[] preferencesKeys = mResources.getStringArray(R.array.preference_keys_array);
        for (int i = 0; i<avaliationItens.length; i++) {
            map.put(avaliationItens[i], preferencesKeys[i]);
        }
        return map;
    }

    public ArrayList<Question> getQuestionsArray(FisicalAvaliation fisicalAvaliations) {
        ArrayList<Question> questions = new ArrayList<>();
        LinkedHashMap<String, String> questionsItens = getPreferencesMap();
        for (Map.Entry<String, String> iten : questionsItens.entrySet()) {
            String question = iten.getKey();
            String preferenceKey = iten.getValue();
            if (isTrakingKey(preferenceKey)) {
                Question.AnswerType answerType = Question.AnswerType.DOUBLE;
                if (question.equals(mResources.getString(R.string.question_date))) {
                    answerType = Question.AnswerType.DATE;
                }
                Question questionObj = new Question(question,
                        answerType,
                        fisicalAvaliations.getValueFromPreferenceKey(preferenceKey, mResources));
                questions.add(questionObj);
            }
        }
        return questions;
    }

}
