/* eslint-disable jsx-a11y/anchor-is-valid */
import { Menu, MenuButton, MenuItem, MenuItems } from "@headlessui/react";
import { ChevronDownIcon } from "@heroicons/react/20/solid";

export default function Dropdown({setCurrentMap}) {

	const items = ['streets-v12', 'outdoors-v12', 'light-v11', 'dark-v11', 'satellite-v9', 'satellite-streets-v12', 'navigation-day-v1', 'navigation-night-v1']

	return (
		<Menu as="div" className="menuclass">
			<div>
				<MenuButton className="menubutton">
					Options
					<ChevronDownIcon
						aria-hidden="true"
						className="iconclass"
					/>
				</MenuButton>
			</div>

			<MenuItems
				transition
				className="menuitems"
			>
				<div className="py-1">
					{
					items.map((item, index) => {
						return (
							<MenuItem key={index}>
								<button
									onClick={() => setCurrentMap(item)}
									className="buttonclass"
								>
									{item}
								</button>
							</MenuItem>
							)
						})
					}
				</div>
			</MenuItems>
		</Menu>
	);
}
