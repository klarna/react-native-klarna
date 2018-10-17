// @flow
import * as React from 'react';
import { requireNativeComponent } from 'react-native';

type Props = {
  snippet: string,
  onComplete: ?Function,
};
export default class RNKlarna extends React.Component<Props> {
  static defaultProps = {
    snippet: '',
  };

  onComplete = (callback: ?Function) => ({ nativeEvent }: { nativeEvent: Object }) => {
    console.log(nativeEvent);
    if (callback) {
      callback(nativeEvent);
    }
  };

  render() {
    const { snippet, onComplete } = this.props;
    return <Klarna flex={1} snippet={snippet} onComplete={this.onComplete(onComplete)} />;
  }
}

const Klarna = requireNativeComponent('RNKlarna', RNKlarna, {
  nativeOnly: {
    onComplete: true,
  },
});
