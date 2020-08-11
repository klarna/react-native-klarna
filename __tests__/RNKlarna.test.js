import 'jest';
import 'react-native';
import RNKlarna from '../index';

it('should render RNKlarna', function() {

  const props = {
    snippet: 'snip',
    onComplete: (response) => {

    },
  };
  const klarna = new RNKlarna(props);
  expect(klarna.props).toEqual(props);

  const rendered = klarna.render();
  expect(rendered.props.children).toBeTruthy();
  expect(rendered.props.children.props.snippet).toEqual('snip');
});