//@ flow
import * as React from 'react';
import { requireNativeComponent } from 'react-native';

export default class RNKlarna extends React.Component {
  render() {
    return <Klarna flex={1} />;
  }
}

const Klarna = requireNativeComponent('RNKlarna', RNKlarna);
