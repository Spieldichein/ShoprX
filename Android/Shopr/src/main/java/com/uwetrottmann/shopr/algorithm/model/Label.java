
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
        A55_DSL("55 DSL"), //TODO new brand - classify stereotypes
        A7_FOR_ALL_MANKIND("7 for all mankind"), //TODO new brand - classify stereotypes
        ADIDAS("Adidas"),
        //        AJC("AJC"),
//        ALBA_MODA("Alba Moda"),
//        ALBERTO("Alberto"),
//        ALDO("Aldo"),
//        ALL_DAY("All Day"),
        ALLEGRA_K("Allegra K"),
        ALPHA_INDUSTRIES("Alpha Industries"), //TODO new brand - classify stereotypes
        //        ALTAMONT("Altamont"),
        AMERICAN_COLLEGE("American College"), //TODO new brand - classify stereotypes
        ANDREA_N_RENATO("Andrea & Renato"), //TODO new brand - classify stereotypes
        ANERKJENDT("Anerkjendt"), //TODO new brand - classify stereotypes
        ANNA_FIELD("Anna Field"), //TODO new brand - classify stereotypes
        ANTONY_MORATO("Antony Morato"), //TODO new brand - classify stereotypes
        //        APRIORI("Apriori"),
//        ARCTERYX("Arcteryx"),
//        ARMANI("Armani"),
        ARMY_OF_ME("Army of Me"), //TODO new brand - classify stereotypes
        ATELIER_GARDEUR("Atelier Gardeur"), //TODO new brand - classify stereotypes
        BANANA_MOON("Banana Moon"), //TODO new brand - classify stereotypes
        BCBGENERATION("BCBGeneration"), //TODO new brand - classify stereotypes
        BEACH_PANTIES("Beach Panties"), //TODO new brand - classify stereotypes
        BELLFIELD("Bellfield"), //TODO new brand - classify stereotypes
        BELLYBUTTON("bellybutton"), //TODO new brand - classify stereotypes
        BEN_SHERMAN("Ben Sherman"), //TODO new brand - classify stereotypes
        BENCH("Bench"), //TODO new brand - classify stereotypes
        BENETTON("Benetton"), //TODO new brand - classify stereotypes
        BIKINI_BAR("Bikini Bar"), //TODO new brand - classify stereotypes
        BITCHING_N_JUNKFOOD("Bitching & Junkfood"), //TODO new brand - classify stereotypes
        BOOB_DESIGN("Boob Design"), //TODO new brand - classify stereotypes
        BOOM_BAP("Boom Bap"),
        BOSS_ORANGE("BOSS Orange"), //TODO new brand - classify stereotypes
        BOXEUR_DES_RUES("Boxeur Des Rues"), //TODO new brand - classify stereotypes
        //        BRANDIT("Brandit"),
        BRAX("Brax"),
        BREAD_N_BOXERS("Bread & Boxers"), //TODO new brand - classify stereotypes
        BRIGITTE_BARDOT("Brigitte Bardot"), //TODO new brand - classify stereotypes
        BRIXTOL("Brixtol"), //TODO new brand - classify stereotypes
        //        BRUNO_BANANI("Bruno Banani"),
        BRUNOTTI("Brunotti"), //TODO new brand - classify stereotypes
        BUFFALO("Buffalo"), //TODO new brand - classify stereotypes
        BUGATTI("Bugatti"), //TODO new brand - classify stereotypes
        //        BURBERRY("Burberry"),
        BURTON("Burton"), //TODO new brand - classify stereotypes
        BYE_BYE_KITTY("Bye bye Kitty"),
        C_DIOR("C. Dior"),
        CARHARTT("Carhartt"),
        //        CASA_MODA("Casa Moda"),
        CALANDO("CALANDO"), //TODO new brand - classify stereotypes
        CALVIN_KLEIN("Calvin Klein"), //TODO new brand - classify stereotypes
        CAMEL_ACTIVE("camel active"), //TODO new brand - classify stereotypes
        CANADIAN_CLASSICS("Canadian Classics"), //TODO new brand - classify stereotypes
        CECEBA("Ceceba"), //TODO new brand - classify stereotypes
        CELIO("CELIO"), //TODO new brand - classify stereotypes
        CHAMPION("Champion"), //TODO new brand - classify stereotypes
        CHANEL("Chanel"),
        CHEAP_MONDAY("Cheap Monday"), //TODO new brand - classify stereotypes
        CHEVIGNON("Chevignon"), //TODO new brand - classify stereotypes
        CHIEMSEE("Chiemsee"), //TODO new brand - classify stereotypes
        CHRISTOFF("Christoff"), //TODO new brand - classify stereotypes
        CIGNO_NERO("Cigno Nero"), //TODO new brand - classify stereotypes
        CINQUE("Cinque"), //TODO new brand - classify stereotypes
        CITIZENS_OF_HUMANITY("Citizens of Humanity"), //TODO new brand - classify stereotypes
        CLOSET("Closet"), //TODO new brand - classify stereotypes
        CMP_F_LLI_CAMPAGNOLO("CMP F.lli Campagnolo"), //TODO new brand - classify stereotypes
        COLUMBIA("Columbia"), //TODO new brand - classify stereotypes
        COMMA("comma,"), //TODO new brand - classify stereotypes
        CONVERSE("Converse"),
        CREAM("Cream"), //TODO new brand - classify stereotypes
        CROSS_JEANSWEAR("Cross Jeanswear"), //TODO new brand - classify stereotypes
        CUPCAKECULT("Cupcakecult"),
        CRAGHOPPERS("Craghoppers"), //TODO new brand - classify stereotypes
        CRIMINAL_DAMAGE("Criminal Damage"), //TODO new brand - classify stereotypes
        D_GNAK("D.GNAK"), //TODO new brand - classify stereotypes
        DAGMAR("Dagmar"), //TODO new brand - classify stereotypes
        DALE_OF_NORWAY("Dale of Norway"), //TODO new brand - classify stereotypes
        DANIEL_HECHTER("Daniel Hechter"), //TODO new brand - classify stereotypes
        DC("DC"),
        //        DEAR_CASHMERE("Dear Cashmere"),
        DENIM("Denim"),
        DERBE("Derbe"), //TODO new brand - classify stereotypes
        DESIGUAL("Desigual"), //TODO new brand - classify stereotypes
        DICKIES("Dickies"),
        DIESEL("Diesel"),
        DIMENSIONE_DANZA("Dimensione Danza"), //TODO new brand - classify stereotypes
        DKNY_INTIMATES("DKNY Intimates"), //TODO new brand - classify stereotypes
        DOCKERS("DOCKERS"), //TODO new brand - classify stereotypes
        DREIMASTER("Dreimaster"), //TODO new brand - classify stereotypes
        ELEVEN_PARIS("Eleven Paris"), //TODO new brand - classify stereotypes
        ELEMENT("Element"), //TODO new brand - classify stereotypes
        ELVINE("Elvine"), //TODO new brand - classify stereotypes
        EMOI_EN_PLUS("Emoi en Plus"), //TODO new brand - classify stereotypes
        ESCADA("Escada"), //TODO new brand - classify stereotypes
        ESPRIT("Esprit"),
        ETERNA("Eterna"), //TODO new brand - classify stereotypes
        ETNIES("Etnies"),
        EVEN_N_ODD("Even&Odd"), //TODO new brand - classify stereotypes
        EVISU("Evisu"), //TODO new brand - classify stereotypes
        EXPRESSO("Expresso"), //TODO new brand - classify stereotypes
        EZEKIEL("Ezekiel"), //TODO new brand - classify stereotypes
        FAME_ON_YOU_PARIS("FAME ON YOU PARIS"), //TODO new brand - classify stereotypes
        FARAH_VINTAGE("Farah Vintage"), //TODO new brand - classify stereotypes
        //        FASHIONSTYLES("FashionStyles"),
        FILIPPA_K("Filippa K"), //TODO new brand - classify stereotypes
        FIRETRAP("Firetrap"), //TODO new brand - classify stereotypes
        FJÄLLRÄVEN("Fjällräven"),
        FOREVER_21("Forever 21"),
        FORVERT("Forvert"), //TODO new brand - classify stereotypes
        FRAAS("Fraas"), //TODO new brand - classify stereotypes
        FRANKIE_GARAGE("Frankie Garage"), //TODO new brand - classify stereotypes
        FRANKLIN_N_MARSHALL("Franklin & Marshall"), //TODO new brand - classify stereotypes
        FREAKY_NATION("Freaky Nation"), //TODO new brand - classify stereotypes
        FREEMAN_T_PORTER("Freeman T. Porter"), //TODO new brand - classify stereotypes
        G_STAR("G-Star"),
        GAASTRA_BLUE("Gaastra Blue"), //TODO new brand - classify stereotypes
        GANT("Gant"), //TODO new brand - classify stereotypes
        GLOVERALL("Gloverall"), //TODO new brand - classify stereotypes
        GOOSECRAFT("Goosecraft"), //TODO new brand - classify stereotypes
//        GOTTEX("Gottex"),
//        GOJIRA("Gojira"),
        GUCCI("Gucci"),
        GUESS("Guess"), //TODO new brand - classify stereotypes
        H_N_M("H & M"),
        //        HACKETT("Hackett"),
//        HAGLÖFS("Haglöfs"),
        HANRO("Hanro"), //TODO new brand - classify stereotypes
        HARDY_AMIES("Hardy Amies"), //TODO new brand - classify stereotypes
        HARRINGTON("HARRINGTON"), //TODO new brand - classify stereotypes
        //        HARVEST("Harvest"),
//        HEINE("Heine"),
        HELLBUNNY("Hellbunny"),
        HOM("HOM"), //TODO new brand - classify stereotypes
        HRLONDON("Hrlondon"),
        HUGO_BOSS("Hugo Boss"),
        HYPE("Hype"), //TODO new brand - classify stereotypes
        INNOCENT("Innocent"),
        INWEAR("InWear"), //TODO new brand - classify stereotypes
        J_CREW("J Crew"),
        J_LINDEBERG("J.LINDEBERG"), //TODO new brand - classify stereotypes
        JACK_WOLFSKIN("Jack Wolfskin"), //TODO new brand - classify stereotypes
        JACK_N_JONES("Jack & Jones"), //TODO new brand - classify stereotypes
        JAPAN_RAGS("JAPAN RAGS"), //TODO new brand - classify stereotypes
        JEFF("Jeff"), //TODO new brand - classify stereotypes
        JOCKEY("Jockey"), //TODO new brand - classify stereotypes
        JOFAMA("Jofama"), //TODO new brand - classify stereotypes
        JOHN_VARVATOS_STAR_USA("John Varvatos Star U.S.A."), //TODO new brand - classify stereotypes
        JOOP("JOOP!"), //TODO new brand - classify stereotypes
        JUST_FEMALE("JUST FEMALE"), //TODO new brand - classify stereotypes
        K_WAY("K-Way"), //TODO new brand - classify stereotypes
        KAFFE("Kaffe"), //TODO new brand - classify stereotypes
        KHUJO("Khujo"), //TODO new brand - classify stereotypes
        KILIWATCH("Kiliwatch"), //TODO new brand - classify stereotypes
        KILIAN_KERNER_SENSES("Kilian Kerner Senses"), //TODO new brand - classify stereotypes
        KIOMI("KIOMI"), //TODO new brand - classify stereotypes
        KIWI_SAINT_TROPEZ("Kiwi Saint Tropez"), //TODO new brand - classify stereotypes
        KNOWLEDGE_COTTON_APPAREL("Knowledge Cotton Apparel"), //TODO new brand - classify stereotypes
        KOOKAI("Kookai"), //TODO new brand - classify stereotypes
        KORINTAGE_BY_SERGE_PARIENTE("Korintage by Serge Pariente"), //TODO new brand - classify stereotypes
        LA_CITY("LA CITY"), //TODO new brand - classify stereotypes
        LACOSTE("Lacoste"),
        //        LAGENLOOK("Lagenlook"),
        LAGERFELD("LAGERFELD"), //TODO new brand - classify stereotypes
        LAONA("Laona"), //TODO new brand - classify stereotypes
        LASCANA("LASCANA"), //TODO new brand - classify stereotypes
        //        LAURA_KENT("Laura Kent"),
        LEE("Lee"), //TODO new brand - classify stereotypes
        //        LEE_CAMERON("Lee Cameron"),
//        LENUS("Lenus"),
        LES_ECLAIRES_BORN_IN_FRANCE("Les Eclaires Born in France"), //TODO new brand - classify stereotypes
        LEVI_S("Levi's"),
        LIGHTNING_BOLT("Lightning Bolt"), //TODO new brand - classify stereotypes
        LINGADORE("LingaDore"), //TODO new brand - classify stereotypes
        LIPSY("Lipsy"), //TODO new brand - classify stereotypes
        LITTLE_MISTRESS("Little Mistress"), //TODO new brand - classify stereotypes
        LIVIA("Livia"), //TODO new brand - classify stereotypes
        LIVINGDEADSOULS("Livingdeadsouls"),
        LJUNG("Ljung"), //TODO new brand - classify stereotypes
        LOGOSHIRT("LOGOSHIRT"), //TODO new brand - classify stereotypes
        LOREAK_MENDIAN("Loreak Mendian"), //TODO new brand - classify stereotypes
        LOUIS_VUITTON("Louis Vuitton"),
        //        LÖFFLER("Löffler"),
        LRG("LRG"),
        LTB("LTB"), //TODO new brand - classify stereotypes
        LYLE_N_SCOTT("Lyle & Scott"), //TODO new brand - classify stereotypes
        MAKIA("Makia"), //TODO new brand - classify stereotypes
        MAMA_LICIOUS("Mama Licious"), //TODO new brand - classify stereotypes
        MARC_O_POLO("Marc O'Polo"), //TODO new brand - classify stereotypes
        MARINEPOOL("Marinepool"), //TODO new brand - classify stereotypes
        MARMOT("Marmot"), //TODO new brand - classify stereotypes
        MAVI("Mavi"), //TODO new brand - classify stereotypes
        MAZE("Maze"), //TODO new brand - classify stereotypes
        MAZINE("Mazine"),
        MBYM("mbyM"), //TODO new brand - classify stereotypes
        MCGREGOR("McGregor"), //TODO new brand - classify stereotypes
//        MCQ("McQ"),
        MERC("Merc"), //TODO new brand - classify stereotypes
        MEXX("Mexx"), //TODO new brand - classify stereotypes
        MICHAEL_KORS("Michael Kors"), //TODO new brand - classify stereotypes
        MILLET("Millet"), //TODO new brand - classify stereotypes
        MINIMUM("Minimum"), //TODO new brand - classify stereotypes
        MOLLY_BRACKEN("Molly Bracken"), //TODO new brand - classify stereotypes
        MOM2MOM("Mom2moM"), //TODO new brand - classify stereotypes
        MORE_N_MORE("More & More"), //TODO new brand - classify stereotypes
        MORGAN("Morgan"), //TODO new brand - classify stereotypes
        //        MUFFLON("Mufflon"),
        MUSTANG("Mustang"), //TODO new brand - classify stereotypes
        NAF_NAF("NAF NAF"), //TODO new brand - classify stereotypes
        NEEDLE_N_THREAD("Needle & Thread"), //TODO new brand - classify stereotypes
        NEW_YORKER("New Yorker"),
        NIKE("Nike"),
        NOISY_MAY("Noisy May"), //TODO new brand - classify stereotypes
        NOPPIES("Noppies"), //TODO new brand - classify stereotypes
        NUDIE("Nudie"), //TODO new brand - classify stereotypes
        //        NÜMPH("Nümph"),
//        O_NEILL("O Neill"),
        OAKWOOD("Oakwood"), //TODO new brand - classify stereotypes
        ODLO("ODLO"), //TODO new brand - classify stereotypes
        OLYMP_LEVEL_5("Olymp Level 5"), //TODO new brand - classify stereotypes
        OLYMP_LUXOR("Olymp Luxor"), //TODO new brand - classify stereotypes
        ONEPIECE("Onepiece"), //TODO new brand - classify stereotypes
        ONLY("ONLY"), //TODO new brand - classify stereotypes
        ONLY_N_SONS("Only & Sons"), //TODO new brand - classify stereotypes
        OPUS("Opus"), //TODO new brand - classify stereotypes
        ORIGINAL_PENGUIN("Original Penguin"), //TODO new brand - classify stereotypes
        ORLEBAR_BROWN("Orlebar Brown"), //TODO new brand - classify stereotypes
        OSMAN("Osman"), //TODO new brand - classify stereotypes
        OTTO_KERN("Otto Kern"), //TODO new brand - classify stereotypes
        PASSIGATTI("Passigatti"), //TODO new brand - classify stereotypes
        PASSPORT("Passport"), //TODO new brand - classify stereotypes
        PATAGONIA("Patagonia"), //TODO new brand - classify stereotypes
        PATRIZIA_PEPE("Patrizia Pepe"), //TODO new brand - classify stereotypes
//        PAUL_N_JOE_SISTER("Paul & Joe Sister"),
        PEPE("Pepe"),
        //        PETER_GOACH("Peter Goach"),
//        PETROL("Petrol"),
//        PIECES_FUNKY("Pieces Funky"),
        PIER_ONE("Pier One"), //TODO new brand - classify stereotypes
        //        PIERRE_CARDIN("Pierre Cardin"),
//        PIKEE("Pikee"),
//        PIQUÉ("Piqué"),
        PRADA("Prada"),
        //        PRINCESS_GOES_HOLLYWOOD("Princess goes Hollywood"),
//        PROMOD("Promod"),
        PUMA("Puma"), //TODO new brand - classify stereotypes
        QUEEN_MUM("Queen Mum"), //TODO new brand - classify stereotypes
        QUIKSILVER("Quiksilver"), //TODO new brand - classify stereotypes
        //        QUIERO("Quiero"),
//        R_DISPLAY("R.Display"),
//        RACHEL_ROY("Rachel Roy"),
        RALPH_LAUREN("Ralph Lauren"),
        RAINS("Rains"), //TODO new brand - classify stereotypes
        //        REDFIELD("Redfield"),
        REDSKINS("Redskins"), //TODO new brand - classify stereotypes
        REDSOUL("REDSOUL"), //TODO new brand - classify stereotypes
        REEBOK("Reebok"),
        REELL("REELL"), //TODO new brand - classify stereotypes
        REGATTA("Regatta"), //TODO new brand - classify stereotypes
        RELIGION("Religion"), //TODO new brand - classify stereotypes
        REPLAY("Replay"), //TODO new brand - classify stereotypes
        REVOLUTION("Revolution"), //TODO new brand - classify stereotypes
        RIP_CURL("Rip Curl"), //TODO new brand - classify stereotypes
        RIVER_ISLAND("River Island"), //TODO new brand - classify stereotypes
        ROBINSON_LES_BAINS("Robinson Les Bains"), //TODO new brand - classify stereotypes
        ROSEMUNDE("Rosemunde"), //TODO new brand - classify stereotypes
        ROXY("Roxy"), //TODO new brand - classify stereotypes
        RUSTY("Rusty"), //TODO new brand - classify stereotypes
        S_OLIVER("s.Oliver"),
        SAIL_RACING("Sail Racing"), //TODO new brand - classify stereotypes
        SCHIESSER("Schiesser"),  //TODO new brand - classify stereotypes
        SCHOOL_RAG("School Rag"), //TODO new brand - classify stereotypes
        SCHOTT_NYC("Schott NYC"), //TODO new brand - classify stereotypes
        //        SCHUMACHER("Schumacher"),
        SCOTCH_N_SODA("Scotch & Soda"),
        SEAFOLLY("Seafolly"), //TODO new brand - classify stereotypes
        SECOND_FEMALE("Second Female"), //TODO new brand - classify stereotypes
        SEE_U_SOON("See u Soon"), //TODO new brand - classify stereotypes
        SELECTED_FEMME("Selected Femme"), //TODO new brand - classify stereotypes
        SELECTED_HOMME("Selected Homme"), //TODO new brand - classify stereotypes
        SEIDENSTICKER("Seidensticker"), //TODO new brand - classify stereotypes
        SERGE_BLANCO("Serge Blanco"), //TODO new brand - classify stereotypes
        SHINE_ORIGINAL("Shine Original"), //TODO new brand - classify stereotypes
//        SIGNORA_M("Signora M"),
        SIGNUM("Signum"), //TODO new brand - classify stereotypes
        //        SINGH_S_MADAN("Singh S. Madan"),
        SISLEY("Sisley"), //TODO new brand - classify stereotypes
        SOAKED_IN_LUXURY("Soaked in Luxury"), //TODO new brand - classify stereotypes
        SOFT_REBELS("Soft Rebels"), //TODO new brand - classify stereotypes
        SOLID("Solid"), //TODO new brand - classify stereotypes
        SOULLAND("Soulland"), //TODO new brand - classify stereotypes
//        SPACE_STYLE("Space Style"),
//        SPEEDO("Speedo"),
        SPIRAL("Spiral"),
        STRAND("Strand"), //TODO new brand - classify stereotypes
        SUBLEVEL("Sublevel"), //TODO new brand - classify stereotypes
        SUNSPEL("Sunspel"), //TODO new brand - classify stereotypes
        SUPERDRY("Superdry"),
        SUPERTRASH("Supertrash"), //TODO new brand - classify stereotypes
        SWEET_PANTS("Sweet Pants"), //TODO new brand - classify stereotypes
        SWING("Swing"), //TODO new brand - classify stereotypes
        TED_BAKER("Ted Baker"), //TODO new brand - classify stereotypes
        TEDDY_SMITH("Teddy Smith"), //TODO new brand - classify stereotypes
        TFNC("TFNC"), //TODO new brand - classify stereotypes
        THE_NORTH_FACE("The North Face"), //TODO new brand - classify stereotypes
        TIFFOSI("Tiffosi"), //TODO new brand - classify stereotypes
        TIGER_OF_SWEDEN("Tiger of Sweden"), //TODO new brand - classify stereotypes
        TIMBERLAND("Timberland"), //TODO new brand - classify stereotypes
        TOM_JOULE("Tom Joule"), //TODO new brand - classify stereotypes
        TOM_TAILOR("Tom Tailor"),
        TOMMY_HILFIGER("Tommy Hilfiger"),
        //        TRIGEMA("Trigema"),
        TRUE_RELIGION("True Religion"), //TODO new brand - classify stereotypes
        TWENTY8TWELVE("Twenty8Twelve"), //TODO new brand - classify stereotypes
        TWINTIP("TWINTIP"), //TODO new brand - classify stereotypes
        UCON_ACROBATICS("Ucon Acrobatics"), //TODO new brand - classify stereotypes
        URBAN_CLASSICS("Urban Classics"), //TODO new brand - classify stereotypes
        VAN_LAACK("van Laack"), //TODO new brand - classify stereotypes
        VANS("Vans"),
        VAUDE("Vaude"), //TODO new brand - classify stereotypes
        VELOUR("Velour"), //TODO new brand - classify stereotypes
        VENICE_BEACH("Venice Beach"), //TODO new brand - classify stereotypes
        VERSACE("Versace"),
        VERO_MODA("Vero Moda"), //TODO new brand - classify stereotypes
        //        VICOMTE("Vicomte"),
        VILA("Vila"),//TODO new brand - classify stereotypes
        VOLCOM("Volcom"), //TODO new brand - classify stereotypes
        VOSSEN("Vossen"), //TODO new brand - classify stereotypes
        WAL_G("WAL G."), //TODO new brand - classify stereotypes
        WATERCULT("watercult"), //TODO new brand - classify stereotypes
        WRANGLER("Wrangler"), //TODO new brand - classify stereotypes
        WOOD_WOOD("Wood Wood"), //TODO new brand - classify stereotypes
        //        WOOLMARK("Woolmark"),
//        X_TWO("X-two"),
//        YAKUZA("Yakuza"),
        YOUR_TURN("YOUR TURN"), //TODO new brand - classify stereotypes
        ZALANDO("Zalando"), //TODO new brand - classify stereotypes
        //        ZERRES("Zerres"),
//        ZOE_PHOBIC("Zoe Phobic"),
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
//        } else if (value.length() == 0) {
//            Log.d("Brand", "Brand not set");
//            setWeights(Value.NOT_SET);
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
            Log.d("Label", "Unknown: " + value.toUpperCase().replace(" ", "_") + "(\"" + value + "\"), //TODO new brand - classify stereotypes");
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
