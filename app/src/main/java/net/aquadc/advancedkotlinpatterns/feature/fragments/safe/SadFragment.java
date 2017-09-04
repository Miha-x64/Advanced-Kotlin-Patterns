package net.aquadc.advancedkotlinpatterns.feature.fragments.safe;

import android.app.Fragment;
import android.os.Bundle;

@SuppressWarnings("unused")
public class SadFragment extends Fragment {

    /**
     * @deprecated no-arg constructor for framework
     */
    public SadFragment() {}

    // Error: Avoid non-default constructors in fragments:
    //        use a default constructor plus
    //        Fragment#setArguments(Bundle) instead.
    public SadFragment(FoodListFragment.DataSource dataSource) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("dataSource", dataSource);
        setArguments(bundle);
    }

}
