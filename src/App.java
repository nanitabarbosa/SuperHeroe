import java.util.Scanner;
import java.util.ArrayList;

class Personaje {
    String nombre;
    int fuerza;
    int velocidad;
    int vida_hp;
    int multiplicador = 2;
    int recuperador_hp = 20;

    public Personaje(String nombre, int fuerza, int velocidad, int vida_hp) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.vida_hp = vida_hp;
    }

    public void atacar(Personaje oponente) {
        System.out.println(this.nombre + " ataca a " + oponente.nombre);
        oponente.recibirDanio(this.fuerza);
    }

    public void recibirDanio(int danio) {
        this.vida_hp -= danio;
        System.out.println(this.nombre + " recibe " + danio + " de daño.");
    }

    public void defender(int danio) {
        int danioMitigado = (danio / multiplicador);
        this.vida_hp -= danioMitigado;
        System.out.println(this.nombre + " se defiende y solo recibe " + danioMitigado + " de daño");
    }

    public void atacar(Personaje oponente, int incrementoFuerza) {
        System.out.println(this.nombre + " ataca a " + oponente.nombre);
        oponente.recibirDanio(this.fuerza + incrementoFuerza);
    }

    public void recuperarse() {
        this.vida_hp += recuperador_hp;
        System.out.println(this.nombre + " ha recuperado " + recuperador_hp + " puntos de vida.");
    }
}

class SuperHero extends Personaje {
    public SuperHero(String nombre, int fuerza, int velocidad, int vida_hp) {
        super(nombre, fuerza, velocidad, vida_hp);
    }

    public void ataqueEspecial(Personaje oponente) {
        System.out.println(this.nombre + " usa su ataque especial en " + oponente.nombre);
        oponente.recibirDanio(this.fuerza * multiplicador);
    }
}

class Villano extends Personaje {
    public Villano(String nombre, int fuerza, int velocidad, int vida_hp) {
        super(nombre, fuerza, velocidad, vida_hp);
    }

    public void hacerTrampa(Personaje oponente) {
        System.out.println(this.nombre + " hace trampa y duplica su ataque!");
        oponente.recibirDanio(this.fuerza * multiplicador);
    }
}

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<SuperHero> heroes = new ArrayList<>();
        heroes.add(new SuperHero("Capitana America", 80, 100, 100));
        heroes.add(new SuperHero("Black Widow", 55, 80, 95));
        heroes.add(new SuperHero("Wanda Maximoff", 78, 70, 90));

        ArrayList<Villano> villanos = new ArrayList<>();
        villanos.add(new Villano("Mística", 85, 80, 100));
        villanos.add(new Villano("Nebula", 72, 90, 90));
        villanos.add(new Villano("Hela", 60, 68, 100));

        System.out.println("-----------------------------------------------");
        System.out.println("BIENVENIDOS A LA BATALLA DEL UNIVERSO DE MARVEL");
        System.out.println("-----------------------------------------------");

        System.out.println("Selecciona tu héroe:");
        for (int i = 0; i < heroes.size(); i++) {
            System.out.println((i + 1) + ". " + heroes.get(i).nombre + " [Fuerza: " + heroes.get(i).fuerza
                    + ", Velocidad: " + heroes.get(i).velocidad + ", HP: " + heroes.get(i).vida_hp + "]");
        }

        System.out.println("Elige tu héroe favorito: ");
        int eleccionHeroe = sc.nextInt() - 1;
        SuperHero heroeSeleccionado = heroes.get(eleccionHeroe);

        System.out.println("Selecciona tu villano:");
        for (int i = 0; i < villanos.size(); i++) {
            System.out.println((i + 1) + ". " + villanos.get(i).nombre + " [Fuerza: " + villanos.get(i).fuerza
                    + ", Velocidad: " + villanos.get(i).velocidad + ", HP: " + villanos.get(i).vida_hp + "]");
        }

        System.out.println("Elige tu villano favorito: ");
        int eleccionVillano = sc.nextInt() - 1;
        Villano villanoSeleccionado = villanos.get(eleccionVillano);

        System.out.println("----------------------------------------------------------");
        System.out.println(heroeSeleccionado.nombre + " VS " + villanoSeleccionado.nombre);
        System.out.println("-----------------------------------------------------------");

        for (int ronda = 1; ronda <= 3; ronda++) {
            System.out.println("------------- RONDA " + ronda + " -------------");

            // Turno del héroe
            System.out.println("Turno del héroe:");
            System.out.println("1. Atacar");
            System.out.println("2. Ataque especial");
            System.out.println("3. Aumentar poder y atacar");
            System.out.println("4. Defenderse");
            System.out.println("5. Recuperarse");

            int accionHeroe = sc.nextInt();
            switch (accionHeroe) {
                case 1:
                    heroeSeleccionado.atacar(villanoSeleccionado);
                    break;
                case 2:
                    heroeSeleccionado.ataqueEspecial(villanoSeleccionado);
                    break;
                case 3:
                    System.out.print("¿Cuánto desea aumentar en fuerza? (1-20): ");
                    int incrementoFuerza = sc.nextInt();
                    if (incrementoFuerza >= 1 && incrementoFuerza <= 20) {
                        heroeSeleccionado.atacar(villanoSeleccionado, incrementoFuerza);
                    } else {
                        System.out.println("El incremento de fuerza debe estar entre 1 y 20.");
                    }
                    break;
                case 4:
                    heroeSeleccionado.defender(villanoSeleccionado.fuerza);
                    break;
                case 5:
                    heroeSeleccionado.recuperarse();
                    break;
                default:
                    System.out.println("Opción no válida. El héroe pierde su turno.");
                    break;
            }

            if (villanoSeleccionado.vida_hp <= 0) {
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println(villanoSeleccionado.nombre + " ha sido derrotado por " + heroeSeleccionado.nombre);
                System.out.println("VICTORIA PARA EL HÉROE");
                System.out.println("------------------------------------------------------------------------------");
                return; // Termina el programa
            }

            // Turno del villano
            System.out.println("Turno del villano:");
            System.out.println("1. Atacar");
            System.out.println("2. Hacer trampa");
            System.out.println("3. Defenderse");
            System.out.println("4. Recuperarse");

            int accionVillano = sc.nextInt();
            switch (accionVillano) {
                case 1:
                    villanoSeleccionado.atacar(heroeSeleccionado);
                    break;
                case 2:
                    villanoSeleccionado.hacerTrampa(heroeSeleccionado);
                    break;
                case 3:
                    villanoSeleccionado.defender(heroeSeleccionado.fuerza);
                    break;
                case 4:
                    villanoSeleccionado.recuperarse();
                    break;
                default:
                    System.out.println("Opción no válida. El villano pierde su turno.");
                    break;
            }

            if (heroeSeleccionado.vida_hp <= 0) {
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println(heroeSeleccionado.nombre + " ha sido derrotado por " + villanoSeleccionado.nombre);
                System.out.println("VICTORIA PARA EL VILLANO");
                System.out.println("------------------------------------------------------------------------------");
                return; // Termina el juego
            }
        }

        // Mensaje al final de las tres rondas
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Las rondas han terminado. No hay más energía para continuar la batalla.");
        System.out.println("HEROE: " + heroeSeleccionado.nombre + " HP: " + heroeSeleccionado.vida_hp);
        System.out.println("VILLANO: " + villanoSeleccionado.nombre + " HP: " + villanoSeleccionado.vida_hp);
        System.out.println("------------------------------------------------------------------------------");
    }
}
