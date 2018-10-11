package com.rafhack.testeandroid.form

object Mocks {

    //region Success response
    const val SUCCESS_RESPONSE: String =
            "{\n" +
                    "  \"cells\": [\n" +
                    "    {\n" +
                    "      \"id\": 1,\n" +
                    "      \"type\": 2,\n" +
                    "      \"message\": \"Olá, primeiro se apresente com o seu nome\",\n" +
                    "      \"typefield\": null,\n" +
                    "      \"hidden\": false,\n" +
                    "      \"topSpacing\": 60,\n" +
                    "      \"show\": null,\n" +
                    "      \"required\": false\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 2,\n" +
                    "      \"type\": 1,\n" +
                    "      \"message\": \"Nome completo\",\n" +
                    "      \"typefield\": 1,\n" +
                    "      \"hidden\": false,\n" +
                    "      \"topSpacing\": 35,\n" +
                    "      \"show\": null,\n" +
                    "      \"required\": true\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 4,\n" +
                    "      \"type\": 1,\n" +
                    "      \"message\": \"Email\",\n" +
                    "      \"typefield\": 3,\n" +
                    "      \"hidden\": true,\n" +
                    "      \"topSpacing\": 35,\n" +
                    "      \"show\": null,\n" +
                    "      \"required\": true\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 6,\n" +
                    "      \"type\": 1,\n" +
                    "      \"message\": \"Telefone\",\n" +
                    "      \"typefield\": \"telnumber\",\n" +
                    "      \"hidden\": false,\n" +
                    "      \"topSpacing\": 10,\n" +
                    "      \"show\": null,\n" +
                    "      \"required\": true\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 3,\n" +
                    "      \"type\": 4,\n" +
                    "      \"message\": \"Gostaria de cadastrar meu email\",\n" +
                    "      \"typefield\": null,\n" +
                    "      \"hidden\": false,\n" +
                    "      \"topSpacing\": 35,\n" +
                    "      \"show\": 4,\n" +
                    "      \"required\": false\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 7,\n" +
                    "      \"type\": 5,\n" +
                    "      \"message\": \"Enviar\",\n" +
                    "      \"typefield\": null,\n" +
                    "      \"hidden\": false,\n" +
                    "      \"topSpacing\": 10,\n" +
                    "      \"show\": null,\n" +
                    "      \"required\": true\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}"
    //endregion

    //region Mispelled fields response
    const val MISPELLED_FIELDS_RESPONSE: String = "{\n" +
            "  \"cells\": [\n" +
            "    {\n" +
            "      \"Id\": 1,\n" +
            "      \"types\": 2,\n" +
            "      \"mesage\": \"Olá, primeiro se apresente com o seu nome\",\n" +
            "      \"typfield\": null,\n" +
            "      \"hidde\": false,\n" +
            "      \"topspacing\": 60,\n" +
            "      \"shows\": null,\n" +
            "      \"isRequired\": false\n" +
            "    }\n" +
            "  ]\n" +
            "}"
    //endregion

}