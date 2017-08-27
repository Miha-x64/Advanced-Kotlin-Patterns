package net.aquadc.advancedkotlinpatterns.feature.fragments.safe;

import android.app.Fragment;

public class InspectionWtf extends Fragment {

    /**
     * No-arg constructor for framework
     */
    public InspectionWtf() {}

    // Error: Avoid non-default constructors in fragments:
    //        use a default constructor plus Fragment#setArguments(Bundle) instead.
    public InspectionWtf(Whatever whatever) {
        setArguments(null);
    }

    interface Whatever {}

}
