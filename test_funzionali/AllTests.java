package test_funzionali;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UC1EseguiNuovoEsperimento.class, UC2ImpostaParametriEsperimento.class, UC3AggiungiTono.class,
		UC4EliminaTono.class, UC5ModificaImpostazioniCamera.class })
public class AllTests {

}
