// @flow
import * as React from 'react';
import { requireNativeComponent, View } from 'react-native';

type Props = {
  snippet: string,
  onComplete: ?Function,
  style?: StyleSheet.Styles,
};

export type NativeEvent = {
  data: {
    order_url?: string,
    uri?: string,
  },
  signalType: string,
  target: number,
  type: string,
};

export default class RNKlarna extends React.Component<Props> {
  static defaultProps = {
    snippet: '',
  };

  onComplete = (callback: ?Function) => ({ nativeEvent }: { nativeEvent: Object }) => {
    if (callback) {
      callback(nativeEvent);
    }
  };

  render() {
    const { snippet, onComplete, style } = this.props;
    return (
      <View style={style}>
        <Klarna flex={1} snippet={snippet} onComplete={this.onComplete(onComplete)} />
      </View>
    );
  }
}

const Klarna = requireNativeComponent('RNKlarna', RNKlarna, {
  nativeOnly: {
    onComplete: true,
  },
});
