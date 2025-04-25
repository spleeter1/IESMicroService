import { Route, Routes } from 'react-router-dom';
import MainLayout from '../layouts/MainLayout';
import ProductManagement from '../pages/Product/ProductPage';
import ImportPage from '../pages/Product/ImportPage';
import SupplierImportChartPage from '../pages/Statistic/SupplierImportChartPage';

const AppRouter = () => {
    return (
        <Routes>
            <Route element={<MainLayout />}>
                <Route path="/order-management/pending" />
                <Route path="/order-management/shipping" />
                <Route path="/order-management/direct-delivery" />
                <Route
                    path="/warehouse-management/import"
                    element={<ImportPage />}
                />
                <Route
                    path="/warehouse-management/product"
                    element={<ProductManagement />}
                />
                <Route path="/stats/product-revenue" />
                <Route path="/stats/agent-revenue" />
                <Route
                    path="/stats/supplier-imported"
                    element={<SupplierImportChartPage />}
                />
                <Route path="/stats/time-revenue" />
                <Route path="*" />
            </Route>
        </Routes>
    );
};
export default AppRouter;
