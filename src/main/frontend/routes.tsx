import { protectRoutes } from '@vaadin/hilla-react-auth';
import { RouteObject, createBrowserRouter } from 'react-router-dom';
import MainLayout from './views/@layout';
import AboutHillaView from './views/about-hilla';
import LoginView from './views/login';
import HelloHillaView from './views/@index';

export const routes: RouteObject[] = protectRoutes([
  {
    element: <MainLayout />,
    handle: { title: 'Main' },
    children: [
      {
        path: '/',
        element: <HelloHillaView />,
        handle: { title: 'Hello Hilla', loginRequired: true },
      },
      {
        path: '/about-hilla',
        element: <AboutHillaView />,
        handle: { title: 'About', loginRequired: true },
      },
    ],
  },
  { path: '/login', element: <LoginView /> },
]);

export default createBrowserRouter(routes);
