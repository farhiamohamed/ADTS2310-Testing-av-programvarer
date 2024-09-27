package oslomet.testing;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKontoController {
    @InjectMocks
    private AdminKontoController adminKontoController;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private Sikkerhet sjekk;

    @Test
    public void registrerKonti_LoggetInn() {
        Konto konto = new Konto("01029624748", "123456789",
                1231, "Lønnskonto", "NOK", null);
        when(sjekk.loggetInn()).thenReturn("Innlogget");
        when(adminRepository.registrerKonto(konto)).thenReturn("OK");
        String res = adminKontoController.registrerKonto(konto);

        assertEquals("OK", res);
    }

    @Test
    public void registrerKonti_IkkeLoggetInn() {
        Konto konto = new Konto("01029624748", "123456789",
                1231, "Lønnskonto", "NOK", null);
        when(sjekk.loggetInn()).thenReturn(null);
        String res = adminKontoController.registrerKonto(konto);

        assertEquals("Ikke innlogget", res);
    }
    @Test
    public void hentAlleKonti_OK(){
        Konto konto1 = new Konto("56789873456", "56789087",
                315.40, "Brukskonto", "NOK",null);

        when(sjekk.loggetInn()).thenReturn("56789873456");

        List<Konto> kontoliste = new ArrayList<>();
        kontoliste.add(konto1);

        Mockito.when(adminRepository.hentAlleKonti()).thenReturn(kontoliste);

        List<Konto> resultat22 = adminKontoController.hentAlleKonti();

        assertEquals(kontoliste, resultat22);

    }
    @Test
    public void hentAlleKonti_Feil(){
        when(sjekk.loggetInn()).thenReturn(null);
        Mockito.when(adminRepository.hentAlleKonti()).thenReturn(null);

        List<Konto> resultat2 = adminKontoController.hentAlleKonti();

        assertNull(resultat2);
    }
    @Test
    public void registrerOK(){
        when(sjekk.loggetInn()).thenReturn(null);
        Konto konto1 = new Konto("56789873456", "56789087",
                315.40, "Brukskonto", "NOK",null);

        when(sjekk.loggetInn()).thenReturn("56789873456");

        Mockito.when(adminRepository.registrerKonto(any(Konto.class))).thenReturn("OK");

        String resultat121 = adminKontoController.registrerKonto(konto1);

        assertEquals("OK", resultat121);
    }
    @Test
    public void registrerFeil(){

        Konto konto1 = new Konto("56789873456", "56789087",
                315.40, "Brukskonto", "NOK",null);

        Mockito.when(adminRepository.registrerKonto(any(Konto.class))).thenReturn("Ikke innlogget");

        String resultat121 = adminKontoController.registrerKonto(konto1);

        assertEquals("Ikke innlogget", resultat121);
    }
    @Test
    public void endreOK(){

        Konto konto1 = new Konto("56789873456", "56789087",
                315.40, "Brukskonto", "NOK",null);
        when(sjekk.loggetInn()).thenReturn("56789873456");

        Mockito.when(adminRepository.endreKonto(any(Konto.class))).thenReturn("OK");

        String resultat13 = adminKontoController.endreKonto(konto1);

        assertEquals ("OK", resultat13);
    }
    @Test
    public void endreFeil(){
        Konto konto1 = new Konto("56789873456", "56789087",
                315.40, "Brukskonto", "NOK",null);

        when(sjekk.loggetInn()).thenReturn("56789873456");

        Mockito.when(adminRepository.endreKonto(any(Konto.class))).thenReturn("Ikke innlogget");

        String resultat13 = adminKontoController.endreKonto(konto1);

        assertEquals ("Ikke innlogget", resultat13);
    }
    @Test
    public void slettOk(){
        Mockito.when(adminRepository.slettKonto("56789873456")).thenReturn("OK");

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat31 = adminKontoController.slettKonto("98769877");

        assertEquals("Ikke innlogget", resultat31);
    }
    @Test
    public void slettFeil(){
        Mockito.when(adminRepository.slettKonto("56789873456")).thenReturn("Ikke innlogget");

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat31 = adminKontoController.slettKonto("98769877");

        assertEquals("Ikke innlogget", resultat31);
    }
}
