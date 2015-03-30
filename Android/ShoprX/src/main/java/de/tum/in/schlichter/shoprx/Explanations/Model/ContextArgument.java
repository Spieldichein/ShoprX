package de.tum.in.schlichter.shoprx.Explanations.Model;

/**
 * Created by Nicksteal on 23.03.2015.
 */
public class ContextArgument extends Argument {
    private Context context;

    public ContextArgument(Type type) {
        super(type);
    }

    public ContextArgument(Context context, boolean isPositive) {
        super(isPositive);
        this.context = context;
        this.type = Type.CONTEXT;
    }

    public Context context() {
        return context;
    }

}
