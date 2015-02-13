
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
        A7_FOR_ALL_MANKIND("7 for all mankind"),
        AAIKO("Aaiko"),
        ADIDAS("Adidas"),
        ALLEGRA_K("Allegra K"),
        ALPHA_INDUSTRIES("Alpha Industries"),
        ALYSI("Alysi"),
        AMERICAN_APPAREL("American Apparel"),
        AMERICAN_COLLEGE("American College"),
        AMERICAN_VINTAGE("American Vintage"),
        ANA_ALCAZAR("Ana Alcazar"),
        ANDREA_N_RENATO("Andrea & Renato"), 
        ANERKJENDT("Anerkjendt"),
        ANNA_FIELD("Anna Field"),
        ANNA_SCOTT("Anna Scott"),
        ANTONY_MORATO("Antony Morato"),
        APRICO("aprico"),
        APRIL_MAY("April May"),
        ARMOR_LUX("Armor lux"),
        ARMY_OF_ME("Army of Me"), 
        ATELIER_GARDEUR("Atelier Gardeur"),
        BAMBAM("BamBam"),
        BANANA_MOON("Banana Moon"),
        BCBGENERATION("BCBGeneration"),
        BEACH_PANTIES("Beach Panties"),
        BELLFIELD("Bellfield"),
        BELLYBUTTON("bellybutton"),
        BEN_SHERMAN("Ben Sherman"),
        BENCH("Bench"),
        BENETTON("Benetton"),
        BEST_MOUNTAIN("Best Mountain"),
        BIKINI_BAR("Bikini Bar"),
        BILLABONG("Billabong"),
        BITCHING_N_JUNKFOOD("Bitching & Junkfood"),
        BJOERN_BORG("Björn Borg"),
        BOOB_DESIGN("Boob Design"),
        BOOM_BAP("Boom Bap"),
        BOSS_ORANGE("BOSS Orange"),
        BOXEUR_DES_RUES("Boxeur Des Rues"),
        BP_STUDIO("BP Studio"),
        BRAX("Brax"),
        BREAD_N_BOXERS("Bread & Boxers"),
        BRIGITTE_BARDOT("Brigitte Bardot"),
        BRIXTOL("Brixtol"),
        BROOKLYNS_OWN_BY_ROCAWEAR("Brooklyn's Own by Rocawear"),
        BROOKS_BROTHERS("Brooks Brothers"),
        BROSBI("Brosbi"),
        BRUNOTTI("Brunotti"),
        BRUUNS_BAZAAR("Bruuns Bazaar"),
        BUFFALO("Buffalo"),
        BUGATTI("Bugatti"),
        BURTON("Burton"),
        BYBLOS("Byblos"),
        BYE_BYE_KITTY("Bye bye Kitty"),
        C_DIOR("C. Dior"),
        CARHARTT("Carhartt"),
        CALANDO("CALANDO"),
        CALVIN_KLEIN("Calvin Klein"), 
        CAMEL_ACTIVE("camel active"),
        CANADIAN_CLASSICS("Canadian Classics"),
        CECEBA("Ceceba"), 
        CELIO("CELIO"),
        CENTZS("CENTŽS"),
        CENTS("CENT´S"),
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
        CO("Co"),
        COAST("Coast"),
        COLUMBIA("Columbia"), 
        COMMA("comma,"),
        CONVERSE("Converse"),
        CREAM("Cream"),
        CROSS_JEANSWEAR("Cross Jeanswear"),
        CUPCAKECULT("Cupcakecult"),
        CRAGHOPPERS("Craghoppers"),
        CRIMINAL_DAMAGE("Criminal Damage"),
        CULTURE("Culture"),
        D_GNAK("D.GNAK"),
        DAGMAR("Dagmar"),
        DALE_OF_NORWAY("Dale of Norway"),
        DANIEL_HECHTER("Daniel Hechter"),
        DANTE6("Dante6"),
        DC("DC"),
        DENIM("Denim"),
        DEPT("DEPT"),
        DERBE("Derbe"),
        DEREK_LAM_10_CROSBY("Derek Lam 10 Crosby"),
        DESIGUAL("Desigual"),
        DESIRES("Desires"),
        DICKIES("Dickies"),
        DIESEL("Diesel"),
        DIMENSIONE_DANZA("Dimensione Danza"),
        DKNY_INTIMATES("DKNY Intimates"),
        DOCKERS("DOCKERS"),
        DOROTENNIS("DOROTENNIS"),
        DREIMASTER("Dreimaster"),
        DRY_LAKE("Dry Lake"),
        DUCA_DEL_COSMA("Duca Del Cosma"),
        ELEVEN_PARIS("Eleven Paris"),
        ELEMENT("Element"),
        ELVINE("Elvine"),
        EMILY_VAN_DEN_BERGH("Emily van den Bergh"),
        EMOI_EN_PLUS("Emoi en Plus"),
        ESCADA("Escada"), 
        ESPRIT("Esprit"),
        ESSENTIEL_ANTWERP("Essentiel Antwerp"),
        ETERNA("Eterna"), 
        ETNIES("Etnies"),
        EVEN_N_ODD("Even&Odd"),
        EVISU("Evisu"), 
        EXPRESSO("Expresso"), 
        EZEKIEL("Ezekiel"), 
        FAME_ON_YOU_PARIS("FAME ON YOU PARIS"),
        FARAH_VINTAGE("Farah Vintage"),
        FEVER_LONDON("Fever London"),
        FILIPPA_K("Filippa K"),
        FIRETRAP("Firetrap"), 
        FJÄLLRÄVEN("Fjällräven"),
        FLOWERS_FOR_FRIENDS("flowers for friends"),
        FOREVER_21("Forever 21"),
        FORVERT("Forvert"),
        FRAAS("Fraas"),
        FRACOMINA("Fracomina"),
        FRANCIS_LEON("Francis Leon"),
        FRANKIE_GARAGE("Frankie Garage"), 
        FRANKLIN_N_MARSHALL("Franklin & Marshall"),
        FREAKY_NATION("Freaky Nation"),
        FREE_PEOPLE("Free People"),
        FREEMAN_T_PORTER("Freeman T. Porter"),
        FRIEDA_N_FREDDIES("Frieda & Freddies"),
        FROCK_AND_FRILL("Frock and Frill"),
        G_STAR("G-Star"),
        GAASTRA("Gaastra"),
        GANT("Gant"),
        GAS("GAS"),
        GAUDI("Gaudi"),
        GERRY_WEBER("Gerry Weber"),
        GESTUZ("Gestuz"),
        GIPSY("Gipsy"),
        GLAMOROUS("Glamorous"),
        GLOBE("Globe"),
        GLOVERALL("Gloverall"), 
        GOOSECRAFT("Goosecraft"),
        GORE_RUNNING_WEAR("Gore Running Wear"),
        GSUS_SINDUSTRIES("Gsus sindustries"),
        GUCCI("Gucci"),
        GUESS("Guess"),
        GWYNEDDS("Gwynedds"),
        H_N_M("H & M"),
        HAGLOEFS("Haglöfs"),
        HAMAKI_HO("Hamaki-Ho"),
        HANRO("Hanro"),
        HARDY_AMIES("Hardy Amies"),
        HARRINGTON("HARRINGTON"),
        HELLBUNNY("Hellbunny"),
        HELLY_HANSEN("Helly Hansen"),
        HETREGO("Hetregó"),
        HOM("HOM"),
        HOODLAMB("Hoodlamb"),
        HRLONDON("Hrlondon"),
        HUGO_BOSS("Hugo Boss"),
        HUMAN_SCALES("Human Scales"),
        HURLEY("Hurley"),
        HYPE("Hype"),
        ICEBERG("Iceberg"),
        ICEPEAK("Icepeak"),
        ICHI("ICHI"),
        IKKS("IKKS"),
        INDI_N_COLD("Indi & Cold"),
        INNOCENT("Innocent"),
        INWEAR("InWear"),
        IVKO("Ivko"),
        J_CREW("J Crew"),
        J_LINDEBERG("J.LINDEBERG"),
        JACK_WOLFSKIN("Jack Wolfskin"),
        JACK_N_JONES("Jack & Jones"),
        JAPAN_RAGS("JAPAN RAGS"),
        JEFF("Jeff"),
        JOCKEY("Jockey"),
        JOFAMA("Jofama"),
        JOHN_VARVATOS_STAR_USA("John Varvatos Star U.S.A."), 
        JOOP("JOOP!"),
        JUNAROSE("Junarose"),
        JUST_CAVALLI("Just Cavalli"),
        JUST_FEMALE("JUST FEMALE"),
        K_WAY("K-Way"),
        K1X("K1X"),
        KAFFE("Kaffe"),
        KALA("Kala"),
        KAPORAL("Kaporal"),
        KARL_KANI("Karl Kani"),
        KHUJO("Khujo"),
        KILIWATCH("Kiliwatch"),
        KILIAN_KERNER_SENSES("Kilian Kerner Senses"),
        KING_LOUIE("King Louie"),
        KIOMI("KIOMI"),
        KIWI_SAINT_TROPEZ("Kiwi Saint Tropez"),
        KNOWLEDGE_COTTON_APPAREL("Knowledge Cotton Apparel"),
        KOOKAI("Kookai"),
        KORINTAGE_BY_SERGE_PARIENTE("Korintage by Serge Pariente"),
        KRÜGER_DIRNDL("Krüger Dirndl"),
        LA_CITY("LA CITY"),
        LACOSTE("Lacoste"),
        LAGERFELD("LAGERFELD"),
        LAONA("Laona"),
        LASCANA("LASCANA"),
        LAUREL("Laurel"),
        LE_COQ_SPORTIF("le coq sportif"),
        LE_TEMPS_DES_CERISES("Le Temps Des Cerises"),
        LEE("Lee"),
        LES_ECLAIRES_BORN_IN_FRANCE("Les Eclaires Born in France"),
        LEVI_S("Levi's"),
        LIEBESKIND("Liebeskind"),
        LIGHTNING_BOLT("Lightning Bolt"), 
        LINGADORE("LingaDore"), 
        LIPSY("Lipsy"),
        LIST("List"),
        LITTLE_MARCEL("Little Marcel"),
        LITTLE_MISTRESS("Little Mistress"),
        LITTLE_WHITE_LIES("Little White Lies"),
        LIU_JO_JEANS("Liu Jo Jeans"),
        LIVIA("Livia"), 
        LIVINGDEADSOULS("Livingdeadsouls"),
        LJUNG("Ljung"),
        LK_BENNETT("LK Bennett"),
        LOGOSHIRT("LOGOSHIRT"),
        LOREAK_MENDIAN("Loreak Mendian"),
        LOUCHE("Louche"),
        LOUIS_VUITTON("Louis Vuitton"),
        LRG("LRG"),
        LTB("LTB"),
        LYLE_N_SCOTT("Lyle & Scott"),
        MADS_NORGAARD("Mads Nørgaard"),
        MAKIA("Makia"), 
        MAMA_LICIOUS("Mama Licious"),
        MANGANO("Mangano"),
        MARC_O_POLO("Marc O'Polo"),
        MARCIANO_GUESS("MARCIANO GUESS"),
        MARKUS_LUPFER("Markus Lupfer"),
        MARINEPOOL("Marinepool"),
        MARMOT("Marmot"),
        MAURO_GRIFONI("Mauro Grifoni"),
        MAVI("Mavi"),
        MAZE("Maze"),
        MAZINE("Mazine"),
        MBYM("mbyM"),
        MCGREGOR("McGregor"),
        MELTIN_POT("Meltin Pot"),
        MERC("Merc"), 
        MEXX("Mexx"),
        MICHAEL_KORS("Michael Kors"),
        MILLET("Millet"), 
        MINIMUM("Minimum"),
        MINUS("Minus"),
        MISS_MISS("Miss Miss"),
        MISTER_MARCEL("Mister Marcel"),
        MITCHELL_N_NESS("Mitchell & Ness"),
        MKT_STUDIO("MKT Studio"),
	    MODSTROEM("Modström"),
        MOLLY_BRACKEN("Molly Bracken"),
        MOM2MOM("Mom2moM"),
        MORE_N_MORE("More & More"),
        MORGAN("Morgan"),
        MORRIS("Morris"),
	    MOEVE("Möve"),
	    MUSTANG("Mustang"),
        MUUBAA("muubaa"),
        MYMO("myMo"),
        NAF_NAF("NAF NAF"),
        NAKETANO("Naketano"),
        NAPAPIJRI("Napapijri"),
        NATIVE_YOUTH("Native Youth"),
        NEEDLE_N_THREAD("Needle & Thread"),
        NELL_N_ME("Nell & Me"),
        NEW_LOOK("New Look"),
        NEW_YORKER("New Yorker"),
        NIKE("Nike"),
        NIKITA("Nikita"),
        NIXON("Nixon"),
        NOA_NOA("Noa Noa"),
        NOISY_MAY("Noisy May"), 
        NOPPIES("Noppies"),
        NUDIE("Nudie"), 
        NUEMPH("Nümph"),
        O_2ND("O'2nd"),
        O_NEILL("O'Neill"),
        OAKLEY("Oakley"),
        OAKWOOD("Oakwood"),
        OBJECT("Object"),
        ODLO("ODLO"), 
        OLYMP_LEVEL_5("Olymp Level 5"),
        OLYMP_LUXOR("Olymp Luxor"),
        ONE_GREEN_ELEPHANT("One Green Elephant"),
        ONEPIECE("Onepiece"), 
        ONLY("ONLY"),
        ONLY_N_SONS("Only & Sons"),
        OPENING_CEREMONY("Opening Ceremony"),
        OPUS("Opus"),
        ORIGINAL_PENGUIN("Original Penguin"),
        ORLEBAR_BROWN("Orlebar Brown"),
        OSCAR_JACOBSON("Oscar Jacobson"),
        OSMAN("Osman"),
        OTTO_KERN("Otto Kern"),
        PASSIGATTI("Passigatti"),
        PASSPORT("Passport"),
        PATAGONIA("Patagonia"),
        PATRIZIA_PEPE("Patrizia Pepe"),
        PEPE("Pepe"),
        PETROL_INDUSTRIES("Petrol Industries"),
        PF_BY_PAOLA_FRANI("PF by Paola Frani"),
        PIECES("Pieces"),
        PIER_ONE("Pier One"),
        PIERRE_BALMAIN("Pierre Balmain"),
        PRADA("Prada"),
        PRAIO("Praio"),
        PRINCESS_GOES_HOLLYWOOD("Princess goes Hollywood"),
        PUMA("Puma"),
        QUEEN_MUM("Queen Mum"),
        QUIKSILVER("Quiksilver"),
        R95TH("R95th"),
        RAGWEAR("Ragwear"),
        RALPH_LAUREN("Ralph Lauren"),
        RAINS("Rains"),
        REDSKINS("Redskins"),
        REDSOUL("REDSOUL"),
        REEBOK("Reebok"),
        REELL("REELL"), 
        REGATTA("Regatta"),
        RELIGION("Religion"),
        RELISH("Relish"),
        RENE_DERHY("René Derhy"),
	    RENE_LEZARD("René Lezard"),
        REPEAT("Repeat"),
        REPLAY("Replay"),
        RESTEROEDS("Resteröds"),
        REVOLUTION("Revolution"),
        RICH_N_ROYAL("Rich & Royal"),
        RIP_CURL("Rip Curl"),
        RIVER_ISLAND("River Island"),
        ROBINSON_LES_BAINS("Robinson Les Bains"),
        ROCKANDBLUE("ROCKANDBLUE"),
        ROSE_A_POIS("Rosé a Pois"),
        ROSEMUNDE("Rosemunde"),
        ROXY("Roxy"),
        ROY_ROGERS("Roy Rogers"),
        RUSTY("Rusty"),
        S_OLIVER("s.Oliver"),
        SAIL_RACING("Sail Racing"),
        SAINT_TROPEZ("Saint Tropez"),
        SCHIESSER("Schiesser"),
        SCHOOL_RAG("School Rag"),
        SCHOTT_NYC("Schott NYC"),
        SCOTCH_N_SODA("Scotch & Soda"),
        SEAFOLLY("Seafolly"),
        SECOND_FEMALE("Second Female"),
        SEE_U_SOON("See u Soon"), 
        SELECTED_FEMME("Selected Femme"),
        SELECTED_HOMME("Selected Homme"),
        SEIDENSTICKER("Seidensticker"),
        SERGE_BLANCO("Serge Blanco"),
        SET("SET"),
        SHINE_ORIGINAL("Shine Original"),
        SIGNUM("Signum"),
        SILVIAN_HEACH("Silvian Heach"),
        SISLEY("Sisley"),
        SMASH("Smash"),
        SOAKED_IN_LUXURY("Soaked in Luxury"),
        SOFT_REBELS("Soft Rebels"),
        SOLID("Solid"),
        SOMEWEAR("Somewear"),
        SOULLAND("Soulland"),
        SOYACONCEPT("Soyaconcept"),
        SPARKZ("Sparkz"),
        SPIRAL("Spiral"),
        SPOOKS("Spooks"),
        SPORTALM("Sportalm"),
        ST_EMILE("St. Emile"),
        ST_MARTINS("st-martins"),
        STEFANEL("Stefanel"),
        STELLA_NOVA("Stella Nova"),
        STRAND("Strand"),
        SUBLEVEL("Sublevel"),
        SUNCOO("Suncoo"),
        SUNSPEL("Sunspel"),
        SUPERDRY("Superdry"),
        SUPERTRASH("Supertrash"),
        SWEET_PANTS("Sweet Pants"),
        SWEET_YEARS("Sweet Years"),
        SWING("Swing"),
        TAIFUN("Taifun"),
        TAO("Tao"),
        TED_BAKER("Ted Baker"),
        TEDDY_SMITH("Teddy Smith"),
        TFNC("TFNC"),
        THE_BLUE_UNIFORM("The Blue Uniform"),
        THE_LOCAL_FIRM("The Local Firm"),
        THE_NORTH_FACE("The North Face"),
        TIBI("Tibi"),
        TIFFOSI("Tiffosi"),
        TIGER_OF_SWEDEN("Tiger of Sweden"),
        TIM_LABENDA("Tim Labenda"),
        TIMBERLAND("Timberland"),
        TOM_JOULE("Tom Joule"),
        TOM_TAILOR("Tom Tailor"),
        TOMMY_HILFIGER("Tommy Hilfiger"),
        TRUE_RELIGION("True Religion"),
        TWENTY8TWELVE("Twenty8Twelve"),
        TWINTIP("TWINTIP"),
        UCON_ACROBATICS("Ucon Acrobatics"),
        ULLA_POPKEN("Ulla Popken"),
        UNDER_ARMOUR("Under Armour"),
        UNIQUE("Unique"),
        URBAN_CLASSICS("Urban Classics"),
        US_POLO_ASSN("U.S. Polo Assn."),
        VAN_LAACK("van Laack"),
        VANS("Vans"),
        VAUDE("Vaude"),
        VELOUR("Velour"),
        VENICE_BEACH("Venice Beach"),
        VERSACE("Versace"),
        VERO_MODA("Vero Moda"),
        VERONIQUE_BRANQUINHO("Veronique Branquinho"),
        VILA("Vila"),
        VOI_JEANS("Voi Jeans"),
        VOLCOM("Volcom"),
        VOSSEN("Vossen"),
        WAL_G("WAL G."),
        WANDA_NYLON("Wanda Nylon"),
        WAREHOUSE("Warehouse"),
        WATERCULT("watercult"),
        WESC("WeSC"),
        WHYRED("Whyred"),
        WILDFOX("Wildfox"),
        WITTY_KNITTERS("Witty Knitters"),
        WRANGLER("Wrangler"),
        WOOD_WOOD("Wood Wood"),
        YOUR_TURN("YOUR TURN"),
        YUMI("Yumi"),
        ZALANDO("Zalando"),
        ZERGATIK("Zergatik"),
        ZIZZI("Zizzi"),
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
        } else if (value.startsWith("DC")) {
            setWeights(Value.DC);
        } else if (value.startsWith("khujo")) {
            setWeights(Value.KHUJO);
        } else if (value.startsWith("Ben Sherman")) {
            setWeights(Value.BEN_SHERMAN);
        } else if (value.startsWith("Nike")) {
            setWeights(Value.NIKE);
        } else if (value.startsWith("Schiesser")) {
            setWeights(Value.SCHIESSER);
        } else if (value.startsWith("Gerry Weber")) {
            setWeights(Value.GERRY_WEBER);
        } else if (value.startsWith("Escada")) {
            setWeights(Value.ESCADA);
        } else if (value.startsWith("Schott")) {
            setWeights(Value.SCHOTT_NYC);
        } else if (value.startsWith("Nudie")) {
            setWeights(Value.NUDIE);
        } else if (value.startsWith("Gaastra")) {
            setWeights(Value.GAASTRA);
        } else if (value.startsWith("Your Turn")) {
            setWeights(Value.YOUR_TURN);
        } else if (value.startsWith("Levi's")) {
            setWeights(Value.LEVI_S);
        } else if (value.startsWith("Seidensticker")) {
            setWeights(Value.SEIDENSTICKER);
        } else if (value.startsWith("Brooklyn")) {
            setWeights(Value.BROOKLYNS_OWN_BY_ROCAWEAR);
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
        } else if (value.startsWith("Calvin Klein")) {
            setWeights(Value.CALVIN_KLEIN);
        } else if (value.startsWith("Mexx")) {
            setWeights(Value.MEXX);
        } else if (value.startsWith("KARL LAGERFELD")) {
            setWeights(Value.LAGERFELD);
        } else if (value.startsWith("Tiger of Sweden")) {
            setWeights(Value.TIGER_OF_SWEDEN);
        } else if (value.startsWith("Versace")) {
            setWeights(Value.VERSACE);
        } else if (value.startsWith("Zalando")) {
            setWeights(Value.ZALANDO);
        } else {
            setWeights(Value.NOT_SET);
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
