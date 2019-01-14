# react-native-klarna

## Getting started

`$ npm install react-native-klarna --save`

### Mostly automatic installation

`$ react-native link react-native-klarna`

### Manual installation

#### iOS

##### CocoaPods route

1. In Podfile add `pod 'RNKlarna', :path => '../node_modules/react-native-klarna'`
2. Run `pod install`.
3. Add the following key with your bundle name to your Info.plist:

```
    <key>ReturnURLKlarna</key>
    <string>YOUR_BUNDLE_NAME</string>
```

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-klarna` and add `RNKlarna.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNKlarna.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`

- Add `import com.reactlibrary.RNKlarnaPackage;` to the imports at the top of the file
- Add `new RNKlarnaPackage()` to the list returned by the `getPackages()` method

2.  Append the following lines to `android/settings.gradle`:
    ```gradle
    include ':react-native-klarna'
    project(':react-native-klarna').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-klarna/android')
    ```
3.  Insert the following lines inside the android block in `android/app/build.gradle`:
    ````gradle
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    ```gradle
    add the following line inside the dependencies block:
    ````
    implementation project(':react-native-klarna')
    ````gradle
    and within repositories block of the dependencies block add:
    ```gradle
        maven { url 'https://x.klarnacdn.net/mobile-sdk/'}
    ````
    In summary, the following changes should be made:
    ```gradle
    android {
      ...
      compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
      }
      ...
    }
    dependencies {
      ...
      implementation project(':react-native-klarna')
      ...
      repositories {
        ...
        maven { url 'https://x.klarnacdn.net/mobile-sdk/'}
        ...
      }
    }
    ```
4.  Register an `intent-filter`:

    ```xml
    <intent-filter>
      <action android:name="android.intent.action.VIEW" />
      <category android:name="android.intent.category.DEFAULT" />
      <category android:name="android.intent.category.BROWSABLE" />

      <data android:scheme="<your-custom-scheme>" />
      <data android:host="<your-custom-host>" />
    </intent-filter>
    ```

5.  Make sure that activity is using `launchMode` `singleTask` or `singleTop`:
    ```xml
    <activity
      android:launchMode="singleTask|singleTop">
    ```

## Usage Example (Redux)

```javascript
import RNKlarna from 'react-native-klarna';
import { NativeEvent } from 'react-native-klarna';

import React, { PureComponent } from 'react';
import { connect } from 'react-redux';
...

type Props = ReturnType<typeof mapStateToProps> & typeof mapDispatchToProps;

export class KlarnaScreen extends PureComponent<Props> {
  static defaultProps = {
    snippet: '',
  };

  onComplete = (event: NativeEvent) => {
    const { signalType } = event;
    if (signalType === 'complete') {
      const { getConfirmationSnippet, orderId } = this.props;
      getConfirmationSnippet(orderId);
      /*
      Redux action that makes a call to the backend,
      retrieves the order status and confirmation snippet.
      We then submit update the Klarna component with new snippet
      */
    }
  };

  render() {
    /*
     Get inital snippet from your backend and replace it with a confirmation one
     once the order status is finalised.
     If error occurs, set snippet to 'error' to dismiss loading screen
    */
    let { snippet } = this.props;
    const { finalSnippet, orderStatus, loadError } = this.props;
    if (orderStatus) snippet = finalSnippet;
    if (loadError) snippet = 'error';
    return (
      <View>
        <RNKlarna snippet={snippet} onComplete={this.onComplete} />
        ...
      </View>
    );
  }
}

const mapStateToProps = (state: Store) => ({
  finalSnippet: state.payment.finalSnippet,
  orderStatus: state.payment.orderStatus,
  loadError: state.payment.loadError,
  snippet: state.payment.paymentSnippet,
});

const mapDispatchToProps = {
  getConfirmationSnippet: paymentActions.getConfirmationSnippet,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(KlarnaScreen);
```
