import { Box, Typography } from '@mui/material';
import SupplierTable from './SupplierTable';
import { useEffect, useState } from 'react';
import ProductTable from './ProductTable';

type Supplier = { id: number; name: string };
const ProductPage = () => {
    const [products, setProducts] = useState([]);
    const [selectedSupplier, setSelectedSupplier] = useState<Supplier | null>(
        null
    );

    useEffect(() => {
        if (!selectedSupplier) return;
        const fetchProducts = async () => {
            try {
                const res = await fetch(
                    `/products/api/product/${selectedSupplier?.id}`
                );
                const data = await res.json();
                console.log(data.type);
                console.log(data);
                setProducts(data);
            } catch (err) {
                console.error('Failed to fetch products', err);
            }
        };

        fetchProducts();
    }, [selectedSupplier]);
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
                    padding: 2,
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
                            onAdd={() => {}}
                            onDelete={() => {}}
                            onEdit={() => {}}
                        />
                    </Box>
                </Box>
            </Box>
        </Box>
    );
};
export default ProductPage;
