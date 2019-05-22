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


public class ValidatorEmailTest {

    // true

    @Test
    public void ValidatorEmail_Com_Dominio_True() {
        assertTrue(ValidatorEmail.isValidEmail("name@email.com"));
    }

    @Test
    public void ValidatorEmail_Com_Dominio_Subdominio_True() {
        assertTrue(ValidatorEmail.isValidEmail("name@email.co.uk"));
    }

    // false

    @Test
    public void ValidatorEmail_Sem_dominio_False() {
        assertFalse(ValidatorEmail.isValidEmail("name@email"));
    }

    @Test
    public void ValidatorEmail_Com_Dois_Pontos_False() {
        assertFalse(ValidatorEmail.isValidEmail("name@email..com"));
    }

    @Test
    public void ValidatorEmail_Sem_Nome_De_Usuario_False() {
        assertFalse(ValidatorEmail.isValidEmail("@email.com"));
    }

    @Test
    public void ValidatorEmail_Com_Email_Vazio_False() {
        assertFalse(ValidatorEmail.isValidEmail(""));
    }

    @Test
    public void ValidatorEmail_Com_Email_Nulo_false() {
        assertFalse(ValidatorEmail.isValidEmail(null));
    }
}
