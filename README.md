# NYC-NEARBY


High Level breakdown:


Using Model View Presenter to seperate the layers of logic from the view.

A BaseView and BasePresenter is defined in the base package.

the contract package contains the contracts for Presenter and View interfaces for each Activity and fragment to implement.



Project outline (maybe Update in future release)

the App use google Place API to search local stores to your current location (restaurants, supermarkets,etc)

Currently, the project contains one MainActivity which has a searchview to let you enter your query,
one settingActivity allows you to set query parameter(for example the search radius, and search type).
Two fragments attached to MainActivity, one displays the searchResult list, one displays the details of particular result.


Project current state:

the DataManger class act as a wrapper the the data to be passed in the presenter layer,
Currently, there is not local database. However, if requirement arises, One can write
local dataBase interface to let database manager class to implement.

Benefit:
        1. logic will be implemented in the presenter, the data will be retrived in the data manager.

        2. to replace the database or network library, just change within data manager class, presenter will not be affected.


Potential disadvatage:
        1. Since data manager contains the source of all data, so when pass into the presenter, the presenter will potentially have all of its source.



Solution:
        created another repository layer in between data manager and presenter to filter out unwanted source input.(proxy pattern)



Things would be good to have When scaling up:

        1. use dagger to inject data source into data manager, and inject data manager into presenter, and inject presenter into view.

        2. created baseActivity and baseFragment to define some of the common behaviors, and create generic presenter reference in baseActivity or baseFragment, and create abstract method for concrete activity and fragment to override.

        3. introducing caching strategy for network and database(complicated topic)



UI current issue:

        1. not much efforts are devoted in this tpoci at the moment(will make it more presentable in the future)

        2. hard code value into XML layout, will fix that later.



Current project problems:

        1. tap twice to exit the detail fragment. (Reason: it happens when the searchView is activate.)

        2. Picasso load images to recyclerView too slow.

        3. LocationManager cannot proper track current location when phone crashes.

        4. settingActivty sortBydistance and sortBybestmatch logic issue(need to redesign the UI and logic)





