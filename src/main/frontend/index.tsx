import { AuthProvider } from 'Frontend/util/auth';
import { createElement } from 'react';
import { createRoot } from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';

import router from './routes';

function App() {
  return (
    <AuthProvider>
      <RouterProvider router={router} fallbackElement={<p>Loading...</p>} />
    </AuthProvider>
  );
}

createRoot(document.getElementById('outlet')!).render(createElement(App));
