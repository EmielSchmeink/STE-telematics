import { render, screen } from '@testing-library/react';
import App from '../App';

// A unit-test which verifies whether a link with the text 'learn react' is somewhere in the html page
// Currently fails, as no such link exists
test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
