
# react-native-klarna

## Getting started

`$ npm install react-native-klarna --save`

### Mostly automatic installation

`$ react-native link react-native-klarna`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-klarna` and add `RNKlarna.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNKlarna.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNKlarnaPackage;` to the imports at the top of the file
  - Add `new RNKlarnaPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-klarna'
  	project(':react-native-klarna').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-klarna/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-klarna')
  	```


## Usage
```javascript
import RNKlarna from 'react-native-klarna';

// TODO: What to do with the module?
RNKlarna;
```
  