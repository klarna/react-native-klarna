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
	<string>YOUR</string>
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
    ```
    include ':react-native-klarna'
    project(':react-native-klarna').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-klarna/android')
    ```
3.  Insert the following lines inside the dependencies block in `android/app/build.gradle`:
    ```
      implementation project(':react-native-klarna')
    		implementation fileTree(dir: "libs", include: ["*.jar"])
    ```
        and within repositories block of the dependencies block:
        ```
        	flatDir {
        		dirs "../../node_modules/react-native-klarna/android/libs"
        	}
        ```
4.  You might also need to add the following line within `<application>` element of your AndroidManifest:
    `tools:replace="android:allowBackup"`

## Usage Example

```javascript
// @flow
import RNKlarna from 'react-native-klarna';
import type { NativeEvent } from 'react-native-klarna';

// TODO: What to do with the module?
import React, { PureComponent } from 'react';
import { connect } from 'react-redux';
...

type Props = {
  snippet: string,
  finalSnippet: string,
  orderStatus: boolean,
};

export class KlarnaScreen extends PureComponent<Props> {
  static defaultProps = {
    snippet: '',
    finalSnippet,
  }

  onComplete = (event: NativeEvent) => {
    const { signalType } = event;
    if (signalType === 'complete') {
      const { getConfirmationSnippet, orderId } = this.props;
      getConfirmationSnippet(orderId);
      /*
      redux action that makes a call to the backend,
      retrieves the order status and confirmation snippet that we then submit to
      the Klarna component updating it
      */
    }
  };

  render() {
    const { snippet, finalSnippet, orderStatus } = this.props;
    if (orderStatus) snippet = finalSnippet;
    return (
      <View>
        <RNKlarna snippet={snippet} onComplete={this.onComplete} />
      </View>
    );
  }
}

const mapStateToProps = (state: Store) => ({
  finalSnippet: state.payment.finalSnippet,
  orderStatus: state.payment.orderStatus,
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
