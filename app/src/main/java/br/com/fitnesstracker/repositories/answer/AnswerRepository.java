package br.com.fitnesstracker.repositories.answer;

import br.com.fitnesstracker.models.FisicalAvaliation;

public interface AnswerRepository {

    void createAnswer(String userId, FisicalAvaliation fisicalAvaliation);

}
