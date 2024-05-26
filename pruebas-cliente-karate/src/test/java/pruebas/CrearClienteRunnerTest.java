package pruebas;

import com.intuit.karate.junit5.Karate;

public class CrearClienteRunnerTest{
    @Karate.Test
    Karate crearClienteData(){
        return Karate.run().relativeTo(getClass());
    }
}