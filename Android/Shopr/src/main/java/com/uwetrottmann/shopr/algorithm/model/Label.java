
package com.uwetrottmann.shopr.algorithm.model;

import android.util.Log;

import com.uwetrottmann.shopr.algorithm.model.Attributes.AttributeValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Label extends GenericAttribute {

    public static final String ID = "label";

    public static final Map<String, Value> sAvailableBrands = new HashMap<String, Value>();

    public enum Value implements AttributeValue {
        A0039_ITALY("0039 Italy"),
        A55_DSL("55 DSL"),
        A7_FOR_ALL_MANKIND("7 for all mankind"), //TODO new brand - classify stereotypes
        ADIDAS("Adidas"),
        ALLEGRA_K("Allegra K"),
        ALPHA_INDUSTRIES("Alpha Industries"), //TODO new brand - classify stereotypes
        AMERICAN_COLLEGE("American College"), 
        ANDREA_N_RENATO("Andrea & Renato"), 
        ANERKJENDT("Anerkjendt"),
        ANNA_FIELD("Anna Field"), //TODO new brand - classify stereotypes
        ANTONY_MORATO("Antony Morato"),
        ARMY_OF_ME("Army of Me"), 
        ATELIER_GARDEUR("Atelier Gardeur"), 
        BANANA_MOON("Banana Moon"), //TODO new brand - classify stereotypes
        BCBGENERATION("BCBGeneration"),
        BEACH_PANTIES("Beach Panties"), //TODO new brand - classify stereotypes
        BELLFIELD("Bellfield"),
        BELLYBUTTON("bellybutton"), //TODO new brand - classify stereotypes
        BEN_SHERMAN("Ben Sherman"), //TODO new brand - classify stereotypes
        BENCH("Bench"),
        BENETTON("Benetton"), //TODO new brand - classify stereotypes
        BIKINI_BAR("Bikini Bar"),
        BITCHING_N_JUNKFOOD("Bitching & Junkfood"),
        BOOB_DESIGN("Boob Design"),
        BOOM_BAP("Boom Bap"),
        BOSS_ORANGE("BOSS Orange"), //TODO new brand - classify stereotypes
        BOXEUR_DES_RUES("Boxeur Des Rues"),
        BRAX("Brax"),
        BREAD_N_BOXERS("Bread & Boxers"),
        BRIGITTE_BARDOT("Brigitte Bardot"),
        BRIXTOL("Brixtol"),
        BRUNOTTI("Brunotti"), //TODO new brand - classify stereotypes
        BUFFALO("Buffalo"),
        BUGATTI("Bugatti"), //TODO new brand - classify stereotypes
        BURTON("Burton"), 
        BYE_BYE_KITTY("Bye bye Kitty"),
        C_DIOR("C. Dior"),
        CARHARTT("Carhartt"),
        CALANDO("CALANDO"), //TODO new brand - classify stereotypes
        CALVIN_KLEIN("Calvin Klein"), //TODO new brand - classify stereotypes
        CAMEL_ACTIVE("camel active"),
        CANADIAN_CLASSICS("Canadian Classics"),
        CECEBA("Ceceba"), 
        CELIO("CELIO"), //TODO new brand - classify stereotypes
        CHAMPION("Champion"),
        CHANEL("Chanel"),
        CHEAP_MONDAY("Cheap Monday"),
        CHEVIGNON("Chevignon"),
        CHIEMSEE("Chiemsee"),
        CHRISTOFF("Christoff"),
        CIGNO_NERO("Cigno Nero"),
        CINQUE("Cinque"),
        CITIZENS_OF_HUMANITY("Citizens of Humanity"),
        CLOSET("Closet"),
        CMP_F_LLI_CAMPAGNOLO("CMP F.lli Campagnolo"),
        COLUMBIA("Columbia"), 
        COMMA("comma,"), //TODO new brand - classify stereotypes
        CONVERSE("Converse"),
        CREAM("Cream"), //TODO new brand - classify stereotypes
        CROSS_JEANSWEAR("Cross Jeanswear"),
        CUPCAKECULT("Cupcakecult"),
        CRAGHOPPERS("Craghoppers"),
        CRIMINAL_DAMAGE("Criminal Damage"),
        D_GNAK("D.GNAK"),
        DAGMAR("Dagmar"),
        DALE_OF_NORWAY("Dale of Norway"),
        DANIEL_HECHTER("Daniel Hechter"),
        DC("DC"),
        DENIM("Denim"),
        DERBE("Derbe"),
        DESIGUAL("Desigual"),
        DICKIES("Dickies"),
        DIESEL("Diesel"),
        DIMENSIONE_DANZA("Dimensione Danza"),
        DKNY_INTIMATES("DKNY Intimates"),
        DOCKERS("DOCKERS"), //TODO new brand - classify stereotypes
        DREIMASTER("Dreimaster"),
        ELEVEN_PARIS("Eleven Paris"), //TODO new brand - classify stereotypes
        ELEMENT("Element"),
        ELVINE("Elvine"), 
        EMOI_EN_PLUS("Emoi en Plus"),
        ESCADA("Escada"), 
        ESPRIT("Esprit"),
        ETERNA("Eterna"), 
        ETNIES("Etnies"),
        EVEN_N_ODD("Even&Odd"), //TODO new brand - classify stereotypes
        EVISU("Evisu"), 
        EXPRESSO("Expresso"), 
        EZEKIEL("Ezekiel"), 
        FAME_ON_YOU_PARIS("FAME ON YOU PARIS"),
        FARAH_VINTAGE("Farah Vintage"), 
        FILIPPA_K("Filippa K"), //TODO new brand - classify stereotypes
        FIRETRAP("Firetrap"), 
        FJÄLLRÄVEN("Fjällräven"),
        FOREVER_21("Forever 21"),
        FORVERT("Forvert"), 
        FRAAS("Fraas"), 
        FRANKIE_GARAGE("Frankie Garage"), 
        FRANKLIN_N_MARSHALL("Franklin & Marshall"),
        FREAKY_NATION("Freaky Nation"), 
        FREEMAN_T_PORTER("Freeman T. Porter"), 
        G_STAR("G-Star"),
        GAASTRA_BLUE("Gaastra Blue"),
        GANT("Gant"), //TODO new brand - classify stereotypes
        GLOVERALL("Gloverall"), 
        GOOSECRAFT("Goosecraft"),
        GUCCI("Gucci"),
        GUESS("Guess"), //TODO new brand - classify stereotypes
        H_N_M("H & M"),
        HANRO("Hanro"),
        HARDY_AMIES("Hardy Amies"),
        HARRINGTON("HARRINGTON"), //TODO new brand - classify stereotypes
        HELLBUNNY("Hellbunny"),
        HOM("HOM"), //TODO new brand - classify stereotypes
        HRLONDON("Hrlondon"),
        HUGO_BOSS("Hugo Boss"),
        HYPE("Hype"), 
        INNOCENT("Innocent"),
        INWEAR("InWear"),
        J_CREW("J Crew"),
        J_LINDEBERG("J.LINDEBERG"), //TODO new brand - classify stereotypes
        JACK_WOLFSKIN("Jack Wolfskin"),
        JACK_N_JONES("Jack & Jones"), //TODO new brand - classify stereotypes
        JAPAN_RAGS("JAPAN RAGS"),
        JEFF("Jeff"),
        JOCKEY("Jockey"), //TODO new brand - classify stereotypes
        JOFAMA("Jofama"),
        JOHN_VARVATOS_STAR_USA("John Varvatos Star U.S.A."), 
        JOOP("JOOP!"), //TODO new brand - classify stereotypes
        JUST_FEMALE("JUST FEMALE"),
        K_WAY("K-Way"),
        KAFFE("Kaffe"),
        KHUJO("Khujo"), //TODO new brand - classify stereotypes
        KILIWATCH("Kiliwatch"),
        KILIAN_KERNER_SENSES("Kilian Kerner Senses"),
        KIOMI("KIOMI"), //TODO new brand - classify stereotypes
        KIWI_SAINT_TROPEZ("Kiwi Saint Tropez"),
        KNOWLEDGE_COTTON_APPAREL("Knowledge Cotton Apparel"),
        KOOKAI("Kookai"), //TODO new brand - classify stereotypes
        KORINTAGE_BY_SERGE_PARIENTE("Korintage by Serge Pariente"),
        LA_CITY("LA CITY"),
        LACOSTE("Lacoste"),
        LAGERFELD("LAGERFELD"), //TODO new brand - classify stereotypes
        LAONA("Laona"), //TODO new brand - classify stereotypes
        LASCANA("LASCANA"),
        LEE("Lee"), //TODO new brand - classify stereotypes
        LES_ECLAIRES_BORN_IN_FRANCE("Les Eclaires Born in France"),
        LEVI_S("Levi's"),
        LIGHTNING_BOLT("Lightning Bolt"), 
        LINGADORE("LingaDore"), 
        LIPSY("Lipsy"), 
        LITTLE_MISTRESS("Little Mistress"), 
        LIVIA("Livia"), 
        LIVINGDEADSOULS("Livingdeadsouls"),
        LJUNG("Ljung"), 
        LOGOSHIRT("LOGOSHIRT"), //TODO new brand - classify stereotypes
        LOREAK_MENDIAN("Loreak Mendian"), 
        LOUIS_VUITTON("Louis Vuitton"),
        LRG("LRG"),
        LTB("LTB"), //TODO new brand - classify stereotypes
        LYLE_N_SCOTT("Lyle & Scott"), //TODO new brand - classify stereotypes
        MAKIA("Makia"), 
        MAMA_LICIOUS("Mama Licious"), //TODO new brand - classify stereotypes
        MARC_O_POLO("Marc O'Polo"), //TODO new brand - classify stereotypes
        MARINEPOOL("Marinepool"),
        MARMOT("Marmot"), 
        MAVI("Mavi"), //TODO new brand - classify stereotypes
        MAZE("Maze"), //TODO new brand - classify stereotypes
        MAZINE("Mazine"),
        MBYM("mbyM"), 
        MCGREGOR("McGregor"), 
        MERC("Merc"), 
        MEXX("Mexx"), //TODO new brand - classify stereotypes
        MICHAEL_KORS("Michael Kors"),
        MILLET("Millet"), 
        MINIMUM("Minimum"), //TODO new brand - classify stereotypes
	    MODSTROEM("Modström"), //TODO new brand - classify stereotypes
        MOLLY_BRACKEN("Molly Bracken"),
        MOM2MOM("Mom2moM"), //TODO new brand - classify stereotypes
        MORE_N_MORE("More & More"), //TODO new brand - classify stereotypes
        MORGAN("Morgan"), //TODO new brand - classify stereotypes
	    MOEVE("Möve"), //TODO new brand - classify stereotypes
	    MUSTANG("Mustang"), //TODO new brand - classify stereotypes
        NAF_NAF("NAF NAF"), //TODO new brand - classify stereotypes
        NEEDLE_N_THREAD("Needle & Thread"),
        NEW_YORKER("New Yorker"),
        NIKE("Nike"),
        NOISY_MAY("Noisy May"), 
        NOPPIES("Noppies"), //TODO new brand - classify stereotypes
        NUDIE("Nudie"), //TODO new brand - classify stereotypes
        OAKWOOD("Oakwood"), //TODO new brand - classify stereotypes
        ODLO("ODLO"), 
        OLYMP_LEVEL_5("Olymp Level 5"), //TODO new brand - classify stereotypes
        OLYMP_LUXOR("Olymp Luxor"), 
        ONEPIECE("Onepiece"), 
        ONLY("ONLY"), //TODO new brand - classify stereotypes
        ONLY_N_SONS("Only & Sons"), //TODO new brand - classify stereotypes
        OPUS("Opus"), 
        ORIGINAL_PENGUIN("Original Penguin"),
        ORLEBAR_BROWN("Orlebar Brown"), //TODO new brand - classify stereotypes
        OSMAN("Osman"),
        OTTO_KERN("Otto Kern"),
        PASSIGATTI("Passigatti"),
        PASSPORT("Passport"),
        PATAGONIA("Patagonia"), //TODO new brand - classify stereotypes
        PATRIZIA_PEPE("Patrizia Pepe"),
        PEPE("Pepe"),
        PIER_ONE("Pier One"), //TODO new brand - classify stereotypes
        PRADA("Prada"),
        PUMA("Puma"), //TODO new brand - classify stereotypes
        QUEEN_MUM("Queen Mum"),
        QUIKSILVER("Quiksilver"), //TODO new brand - classify stereotypes
        RALPH_LAUREN("Ralph Lauren"),
        RAINS("Rains"), //TODO new brand - classify stereotypes
        REDSKINS("Redskins"),
        REDSOUL("REDSOUL"),
        REEBOK("Reebok"),
        REELL("REELL"), 
        REGATTA("Regatta"),
        RELIGION("Religion"),
	    RENE_LEZARD("René Lezard"), //TODO new brand - classify stereotypes
        REPLAY("Replay"), 
        REVOLUTION("Revolution"),
        RIP_CURL("Rip Curl"),
        RIVER_ISLAND("River Island"),
        ROBINSON_LES_BAINS("Robinson Les Bains"),
        ROSEMUNDE("Rosemunde"), //TODO new brand - classify stereotypes
        ROXY("Roxy"),
        RUSTY("Rusty"),
        S_OLIVER("s.Oliver"),
        SAIL_RACING("Sail Racing"),
        SCHIESSER("Schiesser"), //TODO new brand - classify stereotypes
        SCHOOL_RAG("School Rag"),
        SCHOTT_NYC("Schott NYC"), //TODO new brand - classify stereotypes
        SCOTCH_N_SODA("Scotch & Soda"),
        SEAFOLLY("Seafolly"), //TODO new brand - classify stereotypes
        SECOND_FEMALE("Second Female"),
        SEE_U_SOON("See u Soon"), 
        SELECTED_FEMME("Selected Femme"), //TODO new brand - classify stereotypes
        SELECTED_HOMME("Selected Homme"), //TODO new brand - classify stereotypes
        SEIDENSTICKER("Seidensticker"), //TODO new brand - classify stereotypes
        SERGE_BLANCO("Serge Blanco"),
        SHINE_ORIGINAL("Shine Original"),
        SIGNUM("Signum"),
        SISLEY("Sisley"), //TODO new brand - classify stereotypes
        SOAKED_IN_LUXURY("Soaked in Luxury"),
        SOFT_REBELS("Soft Rebels"),
        SOLID("Solid"),
        SOULLAND("Soulland"),
        SPIRAL("Spiral"),
        STRAND("Strand"), //TODO new brand - classify stereotypes
        SUBLEVEL("Sublevel"),
        SUNSPEL("Sunspel"),
        SUPERDRY("Superdry"),
        SUPERTRASH("Supertrash"),
        SWEET_PANTS("Sweet Pants"),
        SWING("Swing"),
        TED_BAKER("Ted Baker"),
        TEDDY_SMITH("Teddy Smith"),
        TFNC("TFNC"),
        THE_NORTH_FACE("The North Face"),
        TIFFOSI("Tiffosi"),
        TIGER_OF_SWEDEN("Tiger of Sweden"),
        TIMBERLAND("Timberland"),
        TOM_JOULE("Tom Joule"),
        TOM_TAILOR("Tom Tailor"),
        TOMMY_HILFIGER("Tommy Hilfiger"),
        TRUE_RELIGION("True Religion"),
        TWENTY8TWELVE("Twenty8Twelve"),
        TWINTIP("TWINTIP"),
        UCON_ACROBATICS("Ucon Acrobatics"),
        URBAN_CLASSICS("Urban Classics"),
        VAN_LAACK("van Laack"),
        VANS("Vans"),
        VAUDE("Vaude"),
        VELOUR("Velour"),
        VENICE_BEACH("Venice Beach"),
        VERSACE("Versace"),
        VERO_MODA("Vero Moda"),
        VILA("Vila"),
        VOLCOM("Volcom"),
        VOSSEN("Vossen"),
        WAL_G("WAL G."),
        WATERCULT("watercult"),
        WRANGLER("Wrangler"),
        WOOD_WOOD("Wood Wood"),
        YOUR_TURN("YOUR TURN"),
        ZALANDO("Zalando"),
        NOT_SET("Brand not set");


        private String mDescriptor;

        Value(String descriptor) {
            mDescriptor = descriptor;
        }

        @Override
        public String descriptor() {
            return mDescriptor;
        }

        @Override
        public int index() {
            return ordinal();
        }
    }

    static {
        if (sAvailableBrands.size() == 0) {
            for (Value value1 : Value.values()) {
                sAvailableBrands.put(value1.descriptor(), value1);
            }
        }
    }

    public Label() {
        int numValues = Value.values().length;
        mValueWeights = new double[numValues];
        Arrays.fill(mValueWeights, 1.0 / numValues);
    }

    public Label(Value value) {
        setWeights(value);
    }

    public Label(String value) {
        value = value.trim();
        //Match the given String to a brand.
        if (sAvailableBrands.containsKey(value)) {
            setWeights(sAvailableBrands.get(value));
        } else if (value.startsWith("adidas")) {
            setWeights(Value.ADIDAS);
        } else if (value.startsWith("khujo")) {
            setWeights(Value.KHUJO);
        } else if (value.startsWith("Ben Sherman")) {
            setWeights(Value.BEN_SHERMAN);
        } else if (value.startsWith("Nike")) {
            setWeights(Value.NIKE);
        } else if (value.startsWith("Schiesser")) {
            setWeights(Value.SCHIESSER);
        } else if (value.startsWith("Schott")) {
            setWeights(Value.SCHOTT_NYC);
        } else if (value.startsWith("Nudie")) {
            setWeights(Value.NUDIE);
        } else if (value.startsWith("Levi's")) {
            setWeights(Value.LEVI_S);
        } else if (value.startsWith("Seidensticker")) {
            setWeights(Value.SEIDENSTICKER);
        } else if (value.startsWith("Jack & Jones")) {
            setWeights(Value.JACK_N_JONES);
        } else if (value.startsWith("Hilfiger") || value.startsWith("Tommy Hilfiger")) {
            setWeights(Value.TOMMY_HILFIGER);
        } else if (value.startsWith("Sir Oliver") || value.startsWith("s.Oliver")) {
            setWeights(Value.S_OLIVER);
        } else if (value.startsWith("Gant")) {
            setWeights(Value.GANT);
        } else if (value.startsWith("Pepe")) {
            setWeights(Value.PEPE);
        } else if (value.startsWith("ESPRIT") || value.startsWith("Esprit") || value.startsWith("edc by Esprit")) {
            setWeights(Value.ESPRIT);
        } else if (value.startsWith("Burton")) {
            setWeights(Value.BURTON);
        } else if (value.startsWith("Tom Tailor")) {
            setWeights(Value.TOM_TAILOR);
        } else if (value.startsWith("Mexx")) {
            setWeights(Value.MEXX);
        } else if (value.startsWith("Versace")) {
            setWeights(Value.VERSACE);
        } else if (value.startsWith("Zalando")) {
            setWeights(Value.ZALANDO);
        } else {
            Log.d("Label", "Unknown: " + value.toUpperCase().replace(" ", "_") + "(\"" + value + "\"),");
        }
    }

    private void setWeights(Value value) {
        mValueWeights = new double[Value.values().length];
        Arrays.fill(mValueWeights, 0.0);
        mValueWeights[value.ordinal()] = 1.0;
        currentValue(value);
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public Value[] getValueSymbols() {
        return Value.values();
    }
}
