package UIT.IE303.BUSMAP.saved;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import UIT.IE303.BUSMAP.entities.Route;
import UIT.IE303.BUSMAP.entities.Station;
import UIT.IE303.BUSMAP.route.RouteListFragment;
import UIT.IE303.BUSMAP.station.StationListFragment;

//SaveStateAdapter là adapter cho viewpager2
public class SavedStateAdapter extends FragmentStateAdapter {
    List<Route> routes;
    List<Station> stations;
    public SavedStateAdapter(@NonNull FragmentActivity fragmentActivity, List<Route> routes, List<Station> stations) {
        super(fragmentActivity);
        this.routes = routes;
        this.stations = stations;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
//                2 fragment là route list và station list
            case 0:
                return new RouteListFragment(routes, true);
            case 1:
                return new StationListFragment(stations);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
