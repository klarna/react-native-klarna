/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/emin93/react-native-template-typescript
 *
 * @format
 */

import React, { Fragment } from 'react';
import { SafeAreaView, StyleSheet, View, Text, StatusBar } from 'react-native';
import RNKlarna, { NativeEvent } from 'react-native-klarna';

import { snippet } from './snippet';

const App = () => {
  return (
    <Fragment>
      <StatusBar barStyle="dark-content" />
      <SafeAreaView style={styles.container}>
        <View style={styles.header}>
          <Text style={styles.text}>Header</Text>
        </View>
        <RNKlarna
          style={styles.klarnaContainer}
          snippet={snippet}
          onComplete={(event: NativeEvent) => {
            console.log(event);
          }}
        />
        <View style={styles.footer}>
          <Text style={styles.text}>Footer</Text>
        </View>
      </SafeAreaView>
    </Fragment>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  klarnaContainer: {
    // particular height and width can also be set if needed
    // Note! on android, making the view too narrow is known to lead to the view being cut-off vertically
    flex: 1,
  },
  header: {
    height: 50,
    backgroundColor: '#5e515a',
  },
  footer: {
    height: 50,
    backgroundColor: '#5e515a',
  },
  text: {
    fontSize: 18,
    fontWeight: '500',
    color: '#aaa',
    alignSelf: 'center',
  },
});

export default App;
