import React from 'react';
import ReactDOM from 'react-dom/client'; // Update import for React 18
import { Provider } from 'react-redux';
import { BrowserRouter, BrowserRouter as Router } from 'react-router-dom';
import App from './App';
import store from './redux/store';
import 'bootstrap/dist/css/bootstrap.min.css';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Provider store={store}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>
);
