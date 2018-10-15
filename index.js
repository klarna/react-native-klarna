// @flow
import * as React from 'react';
import { requireNativeComponent } from 'react-native';

type Props = {
  snippet: string,
};
export default class RNKlarna extends React.Component<Props> {
  static defaultProps = {
    snippet: '',
  };
  render() {
    return <Klarna flex={1} snippet={this.props.snippet} />;
  }
}

const Klarna = requireNativeComponent('RNKlarna', RNKlarna, {
  nativeOnly: {
    onComplete: true,
  },
});
