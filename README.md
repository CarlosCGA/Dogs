<h1 align="center">Dogs - Android App</h1>

<p align="center">
    <a href="https://play.google.com/store/apps/details?id=com.cazulabs.dogsapp"><img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" alt="App icon"/></a>
    <br />
    <a href="https://play.google.com/store/apps/details?id=com.cazulabs.dogsapp"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Google_Play_Store_badge_EN.svg/2560px-Google_Play_Store_badge_EN.svg.png" alt="Play Store" width="250"/></a>  
</p>


## MVVM architecture - Structure 
- Core -> Contains helper files.
- Data -> Contains the classes with the data structure used (mainly data classes).
- Domain -> Business logic. It is divided internally into different use cases.
- UI -> (Also called "Presentation"), contains the classes in charge of updating and modifying the
  visual elements of the XML files in the "res" folder. Contains the MVVM packages "View" and 
  "ViewModel" too.


## ⚠️Disclaimer
In this branch the MVVM architecture is being implemented, since the application was created without a defined structure. This is because the main objective of the application was to learn the flow to follow to publish the application in PlayStore. With the current objective of learning how to make a small structure conversion and to be able to implement new functionalities, because the size of the project is going to grow, I have decided to implement the MVVM structure, for a better organization.
