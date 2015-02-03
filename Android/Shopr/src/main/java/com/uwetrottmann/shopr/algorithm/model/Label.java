
package com.uwetrottmann.shopr.algorithm.model;

import android.util.Log;

import com.uwetrottmann.shopr.algorithm.model.Attributes.AttributeValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Label extends GenericAttribute {

    public static final String ID = "label";

    public static final Map<String, Value> availableBrands = new HashMap<String, Value>();

    public enum Value implements AttributeValue {
        A0039_ITALY("0039 Italy"),
        ADIDAS("Adidas"),
        AJC("AJC"),
        ALBA_MODA("Alba Moda"),
        ALBERTO("Alberto"),
        ALDO("Aldo"),
        ALL_DAY("All Day"),
        ALPHA_INDUSTRIES("Alpha Industries"),
        ALTAMONT("Altamont"),
        APRIORI("Apriori"),
        ARCTERYX("Arcteryx"),
        ARMANI("Armani"),
        ARQUEONAUTAS("Arqueonautas"),
        ATLANTIS("Atlantis"),
        AYRTON("Ayrton"),
        BASLER("Basler"),
        BELLYBUTTON("Bellybutton"),
        BELSTAFF("Belstaff"),
        BENCH("Bench"),
        BENTLEY("Bentley"),
        BETTY_BARCLAY("Betty Barclay"),
        BLAUMAX("Blaumax"),
        BRANDIT("Brandit"),
        BRAX("Brax"),
        BRIGITTE_VON_SCHÖNFELS("Brigitte von Schönfels"),
        BRUNO_BANANI("Bruno Banani"),
        BUGATTI("Bugatti"),
        BURBERRY("Burberry"),
        CARHARTT("Carhartt"),
        CASA_MODA("Casa Moda"),
        CALVIN_KLEIN("Calvin Klein"),
        CAMBIO("Cambio"),
        CHANEL("Chanel"),
        CHARLES_VÖGELE("Charles Vögele"),
        CHARMLINE("Charmline"),
        CHILLAZ("Chillaz"),
        CLEPTOMANICX("Cleptomanicx"),
        CLOSED("Closed"),
        COLCCI("Colcci"),
        COMMA("Comma"),
        COMMUNE_DE_PARIS("Commune de Paris"),
        CONTRAST("Contrast"),
        CRAGHOPPERS("Craghoppers"),
        DARE2B("Dare2B"),
        DEAR_CASHMERE("Dear Cashmere"),
        DESIGUAL("Desigual"),
        DIANE_VON_FURSTENBERG("Diane von Furstenberg"),
        DICKIES_DOUBLE("Dickies Double"),
        DIESEL("Diesel"),
        DOCKERS("Dockers"),
        DOLCE_AND_GABBANA("Dolce & Gabbana"),
        DREA("Drea"),
        DREIMASTER("Dreimaster"),
        DRYKORN("Drykorn"),
        E_MURACCHINI("E.Muracchini"),
        EDDIE_BAUER("Eddie Bauer"),
        ELEMENT("Element"),
        ELEVEN_PARIS("Eleven Paris"),
        ENERGIE("Energie"),
        ENZO_LORENZO("Enzo Lorenzo"),
        ERIMA("Erima"),
        ESPRIT("Esprit"),
        FASHIONSTYLES("FashionStyles"),
        FILIPPA_K("Filippa K"),
        FITZ("Fitz"),
        FJÄLLRÄVEN("Fjällräven"),
        FRED_PERRY("Fred Perry"),
        FREESOUL("Freesoul"),
        FROG_BOX("Frog Box"),
        FRÖHLICH("Fröhlich"),
        FUGA("Fuga"),
        FYNCH_HATTON("Fynch-Hatton"),
        G_STAR("G-Star"),
        GAASTRA("Gaastra"),
        GARDEUR("Gardeur"),
        GERRY_WEBER("Gerry Weber"),
        GEOGRAPHICAL_NORWAY("Geographical Norway"),
        GESTUZ("Gestuz"),
        GIN_TONIC("Gin Tonic"),
        GLÖÖCKLER("Glööckler"),
        GLUMANN("Glumann"),
        GOTTEX("Gottex"),
        GOJIRA("Gojira"),
        GUESS("Guess"),
        HACKETT("Hackett"),
        HAGLÖFS("Haglöfs"),
        HARVEST("Harvest"),
        HEINE("Heine"),
        HOM("HOM"),
        HUGO_BOSS("Hugo Boss"),
        INSIGHT("Insight"),
        IRIEDAILY("Iriedaily"),
        JACK_WOLFSKIN("Jack Wolfskin"),
        JACK_N_JONES("Jack&Jones"),
        J_BRAND("J Brand"),
        JAKO("Jako"),
        JAMMER("Jammer"),
        JE_SUIS("Je suis"),
        JET_LAG("Jet Lag"),
        JOCKEY("Jockey"),
        JOOP("Joop!"),
        JOSIE("Josie"),
        JOY("Joy"),
        JUDIE("Judie"),
        KARL_LAGERFELD("Karl Lagerfeld"),
        KAFFE("Kaffe"),
        KEY_LARGO("Key Largo"),
        KHUJO("Khujo"),
        KILLAH("Killah"),
        KIOMI("Kiomi"),
        KMFDM("KMFDM"),
        KOUCLA("KouCla"),
        KR3W("KR3W"),
        L_ÉTE("L'éte"),
        L_ARGENTINA("L'Argentina"),
        LA_MARTINA("La Martina"),
        LACOSTE("Lacoste"),
        LAGENLOOK("Lagenlook"),
        LAMES_HARVEST("Lames Harvest"),
        LARP("Larp"),
        LAURA_KENT("Laura Kent"),
        LEE_CAMERON("Lee Cameron"),
        LENUS("Lenus"),
        LEVI_S("Levi's"),
        LIDEA("Lidea"),
        LIPSY("Lipsy"),
        LISCA("Lisca"),
        LÖFFLER("Löffler"),
        LRG("LRG"),
        LYLE_N_SCOTT("Lyle & Scott"),
        MAC("Mac"),
        MALOJA("Maloja"),
        MAGNUS("Magnus"),
        MARC_CAIN("Marc Cain"),
        MARC_O_POLO("Marc O'Polo"),
        MARLENE("Marlene"),
        MCGREGOR("McGregor"),
        MCQ("McQ"),
        MEXX("Mexx"),
        MILANO("Milano"),
        MINIMUM("Minimum"),
        MISS_SIXTY("Miss Sixty"),
        MONA("Mona"),
        MONKEE("Monkee"),
        MUFFLON("Mufflon"),
        MUSTANG("Mustang"),
        NAPAPIJRI("Napapijri"),
        NEW_ZEALAND_AUCKLAND("New Zealand Auckland"),
        NEXT("Next"),
        NIKE("Nike"),
        NIKITA("Nikita"),
        NUDIE("Nudie"),
        NÜMPH("Nümph"),
        O_NEILL("O Neill"),
        OILILY("Oilily"),
        OLAF_BENZ("Olaf Benz"),
        OLYMP("Olymp"),
        ONLY("Only"),
        ORLEBAR_BROWN("Orlebar Brown"),
        OTTO_KERN("Otto Kern"),
        PALM_BEACH("Palm Beach"),
        PATRIZIA_DINI("Patrizia Dini"),
        PAUL_N_JOE_SISTER("Paul & Joe Sister"),
        PEPE("Pepe"),
        PETER_GOACH("Peter Goach"),
        PETROL("Petrol"),
        PIECES_FUNKY("Pieces Funky"),
        PIERRE_CARDIN("Pierre Cardin"),
        PIKEE("Pikee"),
        PIQUÉ("Piqué"),
        PRINCESS_GOES_HOLLYWOOD("Princess goes Hollywood"),
        PROMOD("Promod"),
        PUMA("Puma"),
        PURE("Pure"),
        QUICKSILVER("Quicksilver"),
        QUIERO("Quiero"),
        R_DISPLAY("R.Display"),
        RACHEL_ROY("Rachel Roy"),
        RALPH_LAUREN("Ralph Lauren"),
        RANCID("Rancid"),
        REDFIELD("Redfield"),
        REELL("Reell"),
        RENÉ_LEZARD("René Lezard"),
        REPEAT("Repeat"),
        REPLAY("Replay"),
        ROBERT_GRAHAM("Robert Graham"),
        ROCKAMORA("Rockamora"),
        ROCK_IM_PARK("Rock im Park"),
        ROCK_REVIVAL("Rock Revival"),
        ROGER_KENT("Roger Kent"),
        RÖSINGER("Rösinger"),
        ROXY("Roxy"),
        S_OLIVER("S.Oliver"),
        SALSA("Salsa"),
        SAMOON("Samoon"),
        SANTA_CRUZ("Santa Cruz"),
        SCHUMACHER("Schumacher"),
        SCOTCH_N_SODA("Scotch & Soda"),
        SELECTED("Selected"),
        SESSUN("Sessun"),
        SHE("She"),
        SHIMANO("Shimano"),
        SIGNORA_M("Signora M"),
        SINGH_S_MADAN("Singh S. Madan"),
        SKUNKFUNK("Skunkfunk"),
        SOLID("Solid"),
        SOUL_DRESS("Soul Dress"),
        SPACE_STYLE("Space Style"),
        SPEEDO("Speedo"),
        STONE_ISLAND("Stone Island"),
        STRELLSON("Strellson"),
        STROKESMAN_S("Strokesman's"),
        SUGARSHOCK("SugarShock"),
        SUIT("Suit"),
        SUNFLAIR("Sunflair"),
        SUPERDRY("Superdry"),
        SUPRIMA("Suprima"),
        TAIFUN("Taifun"),
        TIMEZONE("Timezone"),
        TOM_TAILOR("Tom Tailor"),
        TOMMY_HILFIGER("Tommy Hilfiger"),
        TRIGEMA("Trigema"),
        UNDEFINED("Undefined brand"),
        UVR_CONNECTED("UVR Connected"),
        VAUDE("Vaude"),
        VICOMTE("Vicomte"),
        VILA_CLEAVO("Vila Cleavo"),
        VUTHY("Vuthy"),
        WENZ("Wenz"),
        WINDSOR("Windsor"),
        WOOLMARK("Woolmark"),
        X_TWO("X-two"),
        YAKUZA("Yakuza"),
        ZERRES("Zerres"),
        ZOE_PHOBIC("Zoe Phobic");


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

    public Label() {
        int numValues = Value.values().length;
        mValueWeights = new double[numValues];
        Arrays.fill(mValueWeights, 1.0 / numValues);
    }

    public Label(Value value) {
        setWeights(value);
    }

    public Label(String value) {
        if (availableBrands.size() == 0) {
            for (Value value1 : Value.values()) {
                availableBrands.put(value1.descriptor(), value1);
            }
        }

        value = value.trim();
        //Match the given String to a brand.
        if (availableBrands.containsKey(value)) {
            setWeights(availableBrands.get(value));
        } else if (value.length() == 0) {
            setWeights(Value.UNDEFINED);
        } else if (value.equals("O'Neill")) {
            setWeights(Value.O_NEILL);
        } else if (value.equals("Qiero")) {
            setWeights(Value.QUIERO);
        } else if (value.equals("IrieDaily")) {
            setWeights(Value.IRIEDAILY);
        } else if (value.startsWith("Element")) {
            setWeights(Value.ELEMENT);
        } else if (value.startsWith("LRG")) {
            setWeights(Value.LRG);
        } else if (value.startsWith("Milano")) {
            setWeights(Value.MILANO);
        } else if (value.startsWith("Nudie")) {
            setWeights(Value.NUDIE);
        } else if (value.startsWith("Only")) {
            setWeights(Value.ONLY);
        } else if (value.startsWith("Strellson")) {
            setWeights(Value.STRELLSON);
        } else {
            Log.d("Label", "Unknown: " + value.toUpperCase().replace(" ", "_") + "(\"" + value + "\"), ");
        }
    }

    private void setWeights(Value value){
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
