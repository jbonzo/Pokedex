/**
 * Represents a Pokemon object. Each has a number, a name, and two elemental
 * types, chosen from the PokemonType enumeration.
 *
 * @author  Joe Rossi
 * @version 1.0
 */
public class Pokemon implements Comparable<Pokemon> {

    // ------Instance data here------

    private int num;
    private String name;
    private PokemonType prim, sec;

    /**
     * Constructs a Pokemon object
     *
     * @param num   this Pokemon's unique number
     * @param name  this Pokemon's name
     * @param p this Pokemon's primary type
     * @param s this Pokemon's secondary type
     */
    public Pokemon(int num, String name, PokemonType p, PokemonType s) {
        this.num = num;
        this.name = name;
        prim = p;
        sec = s;
    }

    /**
     * compares two pokemon by number
     * @param  o Pokemon object
     * @return   negative if this is less than other vice versa if greater
     *                    0 if same
     */
    @Override
    public int compareTo(Pokemon o) {
        return this.num - o.num;
    }

    /**
     * checks to see if the two pokemon are equals
     * @param  o other pokemon
     * @return   true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Pokemon)) {
            return false;
        }
        return this.hashCode() == o.hashCode();
    }

    /**
     * assigns unique code to each pokemon
     * @return an int code unique to each pokemon
     */
    @Override
    public int hashCode() {
        return getNumber() * 10 + name.charAt(0) - 'A';
    }

    /**
     * prints the information for each pokemon in a formatted fashion
     * @return formatted string
     */
    @Override
    public String toString() {
        String s = "#%5d: %-12s Primary Type: %-10s Secondary Type: %s";
        return String.format(s, num, name, prim, sec);

    }

    /**
     * @return  the name of this Pokemon
     */
    public String getName() {
        return name;
    }

    /**
     * @return  the unique number of this Pokemon
     */
    public int getNumber() {
        return num;
    }

    /**
     * @return  the primary type of this Pokemon
     */
    public PokemonType getPrimaryType() {
        return prim;
    }

    /**
     * @return  the secondary type of this Pokemon
     */
    public PokemonType getSecondaryType() {
        return sec;
    }
}
