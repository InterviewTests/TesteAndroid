/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.banco.services.app.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class ValidatorNameTest {

    // false -------------------------------

    //haveWords

    @Test
    public void ValidatorName_Com_Caracteres_Especiais_Retorna_False() {
        assertFalse(ValidatorName.haveWords("##"));
    }
    @Test
    public void ValidatorName_Com_Numeros_Retorna_False() {
        assertFalse(ValidatorName.haveWords("123456"));
    }
    @Test
    public void ValidatorName_Com_Espaco_Branco_Retorna_False() {
        assertFalse(ValidatorName.haveWords(" "));
    }
    @Test
    public void ValidatorName_Com_Nulo_Retorna_False() {
        assertFalse(ValidatorName.haveWords(null));
    }
    //haveSize
    @Test
    public void ValidatorName_Menor_Que_02_Letras_Retorna_False() {
        assertFalse(ValidatorName.haveSize("J"));
    }
    @Test
    public void ValidatorName_Maior_Que_25_Letras_Retorna_False() {
        assertFalse(ValidatorName.haveSize("Inconstitucionalissimamente")); // 27 letras
    }
    //haveParts
    @Test
    public void ValidatorName_Menor_Que_02_Palavras_Retorna_False() {
        assertFalse(ValidatorName.haveParts("Jhon")); // 1
    }
    @Test
    public void ValidatorName_Maior_Que_4_Palavras_Retorna_False() {
        assertFalse(ValidatorName.haveParts("Jhon Doe Smith Jhonson Junior")); // 5
    }
    // retorno final
    @Test
    public void ValidatorName_Nome_Com_01_Palavra_Retorna_False() {
        assertFalse(ValidatorName.isValidName("Jhon")); // 1
    }
    @Test
    public void ValidatorName_Nome_Com_05_Palavra_Retorna_False() {
        assertFalse(ValidatorName.isValidName("Jhon Doe Smith Jhonson Junior")); // 5
    }

    @Test
    public void ValidatorName_Nome_Com_Caracteres_Especiais_Retorna_False() {
        assertFalse(ValidatorName.isValidName("Jhon Doe ##"));
    }
    @Test
    public void ValidatorName_Nome_Com_Numeros_Retorna_False() {
        assertFalse(ValidatorName.isValidName("Jhon Doe 12345"));
    }
    @Test

    public void ValidatorName_Nome_Com_Ponto_Retorna_False() {
        assertFalse(ValidatorName.isValidName("Jhon D. Smith"));
    }
    // True  -------------------------------




}