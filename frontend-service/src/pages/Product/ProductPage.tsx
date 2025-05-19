import { Box, Typography } from '@mui/material';
import SupplierTable from './SupplierTable';
import { useEffect, useState } from 'react';
import ProductTable from './ProductTable';
import axios from 'axios';
import ProductModal from './ProductModal';
import api from '../../api/apiClient';

type Supplier = { id: number; name: string };
type ProductForm = {
    name: string;
    salePrice: number;
};
interface Product {
    id: number;
    name: string;
    quantity: number;
    importPrice: number;
    salePrice: number;
    supplierId: number;
    supplierName: string;
}
const ProductPage = () => {
    const [products, setProducts] = useState<Product[]>([]);
    const [selectedSupplier, setSelectedSupplier] = useState<Supplier | null>(
        null
    );
    const [modalOpen, setModalOpen] = useState(false);
    const [editingProduct, setEditingProduct] = useState<Product | null>(null);

    useEffect(() => {
        if (!selectedSupplier) return;
        const fetchProducts = async () => {
            try {
                const res = await fetch(
                    `/products/api/product/${selectedSupplier?.id}`
                );
                const data = await res.json();
                console.log(data);
                setProducts(data);
            } catch (err) {
                console.error('Failed to fetch products', err);
            }
        };

        fetchProducts();
    }, [selectedSupplier]);

    const handleDelete = async (productId: number) => {
        try {
            const res = await api.delete(`/products/api/product/${productId}`);
            console.log('Product deleted successfully', res.data);
            console.log(productId);
            setProducts(prev =>
                prev.filter((product: Product) => product.id !== productId)
            );
            alert('Product deleted successfully');
        } catch (err) {
            console.error('Failed to delete product', err);
            alert('Failed to delete product');
        }
    };

    const openModal = (product: Product) => {
        setModalOpen(true);
        setEditingProduct(product || null);
    };

    const handleModalSubmit = async (data: ProductForm) => {
        if (!selectedSupplier || !editingProduct?.id) {
            alert('Missing product or supplier information.');
            return;
        }

        try {
            const formData = {
                ...data,
                supplierId: selectedSupplier.id,
            };

            const res = await api.put(
                `/products/api/product/${editingProduct.id}`,
                formData,
                {
                    headers: { 'Content-Type': 'application/json' },
                }
            );
            const updated = res.data;
            setProducts(prev =>
                prev.map(p => (p.id === editingProduct.id ? updated : p))
            );
            console.log('Product updated successfully', res.data);
            alert('Product updated successfully');
            setModalOpen(false);
        } catch (err) {
            console.error('Submit error', err);
        }
    };
    return (
        <Box>
            <Typography sx={{ paddingBottom: '10px' }} variant="h3">
                Product Management
            </Typography>

            <Box
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    border: '1px solid #ccc',
                    padding: 1,
                    borderRadius: 2,
                }}
            >
                {/* top parrtrition */}
                {/* <Box
                    sx={{
                        display: 'flex',
                        flexDirection: 'row',
                    }}
                >
                    <Box
                        sx={{
                            width: '50%',
                            justifyContent: 'left',
                            alignItems: 'center',
                            display: 'flex',
                        }}
                    >
                        <Typography variant="h6">Search Supplier: </Typography>
                    </Box>

               
                    <Box
                        sx={{
                            width: '50%',
                            justifyContent: 'right',
                            alignItems: 'center',
                            display: 'flex',
                        }}
                    >
                        <Typography variant="h6">
                            Search Product by Supplier
                        </Typography>
                    </Box>
                </Box> */}

                {/* main partition */}
                <Box
                    sx={{
                        display: 'flex',
                        flexDirection: 'row',
                        // marginTop: 2,
                    }}
                >
                    {/* left main partition */}
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

                    {/* right main partition */}
                    <Box
                        sx={{
                            width: '50%',
                            marginLeft: 1,
                            border: '1px solid #ccc',
                            height: 'calc(100vh - 200px)',
                        }}
                    >
                        {/* <Typography variant="h6"></Typography> */}
                        <ProductTable
                            products={products}
                            // onAdd={() => {}}
                            onDelete={handleDelete}
                            onEdit={openModal}
                        />
                    </Box>
                    <ProductModal
                        open={modalOpen}
                        initialValues={
                            editingProduct
                                ? {
                                      name: editingProduct.name,
                                      salePrice: editingProduct.salePrice,
                                  }
                                : undefined
                        }
                        onClose={() => setModalOpen(false)}
                        onSubmit={handleModalSubmit}
                    />
                </Box>
            </Box>
        </Box>
    );
};
export default ProductPage;
