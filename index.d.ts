import { Component } from 'react';
import { ViewProperties, ViewStyle } from 'react-native';

export interface KlarnaProps {
  snippet: string;
  onComplete: (response: NativeEvent) => void;
  style?: ViewStyle;
}

export class RNKlarna extends Component<KlarnaProps & ViewProperties> {}
export default RNKlarna;

export interface NativeEvent {
  data: {
    order_url?: string;
    uri?: string;
  };
  signalType: string;
  target: number;
  type: string;
}
