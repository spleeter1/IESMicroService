import { Box, Button, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import SupplierTable from './SupplierTable';
import AvailableProductsTable, { Product } from './AvailableProductsTable';
import SelectedProductsTable, {
    SelectedProduct,
} from './SelectedProductsTable';
import NewProductModal from './NewProductModal';
import ImportModal from './ImportModal';
import axios from 'axios';
import api from '../../api/apiClient';

const ImportPage = () => {
    const [products, setProducts] = useState<Product[]>([]);
    const [selectedSupplier, setSelectedSupplier] = useState<{
        id: number;
        name: string;
    } | null>(null);
    const [checkedIds, setCheckedIds] = useState<number[]>([]);
    const [selectedProducts, setSelectedProducts] = useState<SelectedProduct[]>(
        []
    );
    const [editProduct, setEditProduct] = useState<SelectedProduct | null>(
        null
    );
    const [newProductModalOpen, setNewProductModalOpen] = useState(false);

    useEffect(() => {
        if (!selectedSupplier) return;

        const fetchProducts = async () => {
            try {
                const res = await api.get(
                    `/products/api/product/${selectedSupplier.id}`
                );
                setProducts(res.data);
            } catch (err) {
                console.error('Failed to fetch products', err);
            }
        };

        fetchProducts();
    }, [selectedSupplier]);

    const handleCheck = (productId: number) => {
        setCheckedIds(prev =>
            prev.includes(productId)
                ? prev.filter(id => id !== productId)
                : [...prev, productId]
        );
    };

    const handleAddSelected = () => {
        const added = products
            .filter(p => checkedIds.includes(p.id))
            .map(p => ({
                productId: p.id,
                productName: p.name,
                importPrice: p.importPrice,
                quantity: 1,
                salePrice: p.salePrice,
            }));
        setSelectedProducts(prev => [...prev, ...added]);
        setCheckedIds([]);
    };

    const handleRemoveSelected = (productId: number) => {
        setSelectedProducts(prev =>
            prev.filter(p => p.productId !== productId)
        );
    };

    const handleEditSave = (product: SelectedProduct) => {
        setSelectedProducts(prev =>
            prev.map(p => (p.productId === product.productId ? product : p))
        );
        setEditProduct(null);
    };

    const handleAddNewProduct = async (
        product: Omit<Product, 'id' | 'quantity'>
    ) => {
        try {
            const formData = { ...product, quantity: 0 };
            console.log(formData);
            const res = await api.post(
                '/products/api/product/add-product',
                formData
            );
            const added = res.data;
            setProducts(prev => [...prev, added]);
            setNewProductModalOpen(false);
        } catch (e) {
            console.error('Error adding new product:', e);
        }
    };

    const handleSubmit = async () => {
        if (!selectedSupplier) return;
        const payload = {
            supplierId: selectedSupplier.id,
            supplierName: selectedSupplier.name,
            orderDetailList: selectedProducts.map(p => ({
                productId: p.productId,
                productName: p.productName,
                unitPrice: p.importPrice,
                quantity: p.quantity,
            })),
        };
        console.log('Submitting:', payload);
        try {
            const res = await api.post(
                '/import-orders/api/import/create',
                payload
            );
            console.log('Import successful:', res.data);
            setSelectedProducts([]);
            alert('Import successful');

            try {
                const res = await axios.get(
                    `/products/api/product/${selectedSupplier.id}`
                );
                setProducts(res.data);
            } catch (err) {
                console.error('Failed to fetch products, F5 pls', err);
            }
        } catch (err) {
            console.error('Failed to import products', err);
            alert('Failed to import products');
        }
    };

    return (
        <Box>
            <Typography variant="h4" sx={{ mb: 2 }}>
                Import Product
            </Typography>
            <Box display="flex">
                <Box
                    sx={{
                        width: '50%',
                        marginRight: 1,
                        // border: '1px solid #e7e9ef',
                        height: 'calc(100vh - 200px)',
                    }}
                >
                    {/* <Typography variant="h6">Product</Typography> */}

                    <SupplierTable onSelect={setSelectedSupplier} />
                </Box>

                <Box
                    sx={{
                        width: '50%',
                        marginLeft: 1,
                        border: '1px solid #ccc',
                        height: 'calc(100vh - 200px)',
                        display: 'flex',
                        flexDirection: 'column',
                    }}
                >
                    <Box
                        sx={{
                            height: '50%',
                            marginBottom: 1,
                        }}
                    >
                        <AvailableProductsTable
                            products={products}
                            checkedIds={checkedIds}
                            onCheck={handleCheck}
                        />
                        <Button
                            onClick={handleAddSelected}
                            sx={{}}
                            variant="outlined"
                        >
                            Chọn sản phẩm
                        </Button>
                        <Button
                            onClick={() => setNewProductModalOpen(true)}
                            sx={{}}
                            variant="contained"
                        >
                            Add New Product
                        </Button>
                    </Box>
                    <Box sx={{ height: '50%' }}>
                        <SelectedProductsTable
                            products={selectedProducts}
                            onEdit={p => setEditProduct(p)}
                            onRemove={handleRemoveSelected}
                        />
                        <Button
                            variant="contained"
                            onClick={handleSubmit}
                            sx={{}}
                        >
                            Gửi
                        </Button>
                    </Box>
                </Box>

                <ImportModal
                    open={!!editProduct}
                    product={editProduct}
                    onClose={() => setEditProduct(null)}
                    onSave={handleEditSave}
                />

                {selectedSupplier && (
                    <NewProductModal
                        open={newProductModalOpen}
                        onClose={() => setNewProductModalOpen(false)}
                        onSave={handleAddNewProduct}
                        supplierId={selectedSupplier.id}
                        supplierName={selectedSupplier.name}
                    />
                )}
            </Box>
        </Box>
    );
};

export default ImportPage;
