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


public class ValidatorPhoneTest {

    // false -------------------------------


    @Test
    public void ValidatorPhone_Com_Caracteres_Especiais_Retorna_False() {
        assertFalse(ValidatorPhone.isPhoneNumeric("##"));
    }

    @Test
    public void ValidatorPhone_Com_Espaco_Branco_Retorna_False() {
        assertFalse(ValidatorPhone.isPhoneNumeric(" "));
    }

    @Test
    public void ValidatorPhone_Com_Nulo_Retorna_False() {
        assertFalse(ValidatorPhone.isPhoneNumeric(null));
    }


    @Test
    public void ValidatorPhone_Menor_Que_10_Numeros_Retorna_False() {
        assertFalse(ValidatorPhone.isPhoneNumeric("991234567")); // 7
    }


    @Test
    public void ValidatorPhone_Maior_Que_13_Numeros_Retorna_False() {
        assertFalse(ValidatorPhone.isPhoneNumeric("55995555777799")); //14
    }


    //true

    @Test
    public void ValidatorPhone_Igual_10_Numeros_Retorna_True() {
        assertTrue(ValidatorPhone.isPhoneNumeric("1155555555")); // 10
    }

    @Test
    public void ValidatorPhone_Igual_13_Numeros_Retorna_True() {
        assertTrue(ValidatorPhone.isPhoneNumeric("5599555577779")); // 13
    }

    // True  -------------------------------





}