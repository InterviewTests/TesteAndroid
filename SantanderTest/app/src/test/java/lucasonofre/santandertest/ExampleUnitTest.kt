package lucasonofre.santandertest

import lucasonofre.santandertest.view.fragment.ContatoViewListener
import lucasonofre.santandertest.model.ContactItens
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    class contactViewMock: ContatoViewListener {

        private var showSuccessWasCalled = false

        override fun showSuccessPage() {
            showSuccessWasCalled = true
        }

        override fun hideSuccessPage() {

        }

        override fun displayError(message: String) {

        }

        override fun updateRecyclerView(items: ArrayList<ContactItens>) {

        }
    }

    @Test
    fun addition_isCorrect() {

        val view = contactViewMock()

            view.showSuccessPage()


        assertEquals(4, 2 + 2)
    }
}
