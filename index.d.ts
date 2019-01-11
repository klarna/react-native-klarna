import { Component, ReactNode } from 'react';
import { ViewProperties } from 'react-native';

export interface KlarnaProps {
  snippet: string;
  onComplete?(response: NativeEvent): void;
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
